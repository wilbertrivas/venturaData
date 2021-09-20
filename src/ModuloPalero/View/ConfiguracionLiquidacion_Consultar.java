package ModuloPalero.View;
    

import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.TipoArticulo;
import ModuloCarbon.View.MvtoCarbon_GenerarMatriz;
import ModuloPalero.Controller.ControlDB_ConfiguracionLiquidacion;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPersonal.Model.CargoNomina;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;

public final class ConfiguracionLiquidacion_Consultar extends javax.swing.JPanel implements ActionListener, TableModelListener{
    Usuario user;
    private final String tipoConexion;
    ArrayList<TipoArticulo> listadoTipoArticulo = new ArrayList();
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    private PaginadorDeTabla<ConfiguracionLiquidacion> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    ArrayList<ConfiguracionLiquidacion> listado=null;
    public ConfiguracionLiquidacion_Consultar(Usuario us,String tipoConexion) {
        initComponents();
        user = us;
        this.tipoConexion = tipoConexion;
        encabezadoTabla = new ArrayList<String>();
        pageJComboBox.show(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fechaInicio_Consulta = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fechaFin_Consulta = new com.toedter.calendar.JDateChooser();
        consultar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pageJComboBox = new javax.swing.JComboBox<>();
        paginationPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1070, 370));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CONSULTA DE LIQUIDACIONES");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1070, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("FECHA INICIO:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 30));

        fechaInicio_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicio_ConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicio_ConsultaMouseEntered(evt);
            }
        });
        add(fechaInicio_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 170, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("|");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 30, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("FECHA FIN:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, 30));

        fechaFin_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFin_ConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFin_ConsultaMouseEntered(evt);
            }
        });
        add(fechaFin_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 170, 30));

        consultar1.setBackground(new java.awt.Color(255, 255, 255));
        consultar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/consultar.png"))); // NOI18N
        consultar1.setText("CONSULTAR");
        consultar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                consultar1MouseExited(evt);
            }
        });
        consultar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar1ActionPerformed(evt);
            }
        });
        add(consultar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 160, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1070, 420));

        pageJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageJComboBoxActionPerformed(evt);
            }
        });
        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 10, 60, 40));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 1070, 80));
    }// </editor-fold>//GEN-END:initComponents

    private void fechaInicio_ConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_ConsultaMouseClicked

    }//GEN-LAST:event_fechaInicio_ConsultaMouseClicked

    private void fechaInicio_ConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_ConsultaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicio_ConsultaMouseEntered

    private void fechaFin_ConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_ConsultaMouseClicked

    }//GEN-LAST:event_fechaFin_ConsultaMouseClicked

    private void fechaFin_ConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_ConsultaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFin_ConsultaMouseEntered

    private void consultar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultar1MouseExited

    }//GEN-LAST:event_consultar1MouseExited

    private void consultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar1ActionPerformed
        paginationPanel.removeAll();
        validar();
        generarListadoMvtoCarbon();
    }//GEN-LAST:event_consultar1ActionPerformed

    private void pageJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageJComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultar1;
    private com.toedter.calendar.JDateChooser fechaFin_Consulta;
    private com.toedter.calendar.JDateChooser fechaInicio_Consulta;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
   
    public void generarListadoMvtoCarbon(){
        try{
            String fecha_Inicial="";
            String fecha_Final="";
            int contador=0;
            try{
                Calendar fechaI = fechaInicio_Consulta.getCalendar();
                String anoI = ""+fechaI.get(Calendar.YEAR);
                String mesI = "";
                if((fechaI.get(Calendar.MONTH) +1) <=9){
                    mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                }else{
                    mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                }
                String diaI = "";
                if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                    diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                }else{
                    diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                }
                fecha_Inicial=anoI+"-"+mesI+"-"+diaI;
                contador++;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Verifique la fecha de inicio de la consulta", "Error!!.", JOptionPane.ERROR_MESSAGE);
            }
            try{
                Calendar fechaF = fechaFin_Consulta.getCalendar();
                String anoF = ""+fechaF.get(Calendar.YEAR);
                String mesF = "";
                if((fechaF.get(Calendar.MONTH) +1) <=9){
                                mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                }else{
                    mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                }
                String diaF = "";
                if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                    diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                }else{
                    diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                }
                fecha_Final=anoF+"-"+mesF+"-"+diaF;
                contador++;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Verifique la fecha de fin de la consulta", "Error!!.", JOptionPane.ERROR_MESSAGE);
            } 
            if(contador==2){//Se validaron las dos fechas y contienen formato correcto
                try {
                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fecha_Inicial+"'","'"+fecha_Final+"'"));
                    if(resultDosFechas < 0){
                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                    }else{
                        /*if(resultDosFechas ==0){
                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                        }else{*/
                            tabla_Listar(fecha_Inicial,fecha_Final);
                        //}
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_GenerarMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se puedo procesar el descargue de Carbon", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
    }
    public void tabla_Listar(String data1, String data2) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_ConfiguracionLiquidacion(tipoConexion).buscarConfiguracionesLiquidaciones(data1, data2);
        if(listado != null){
            proveedorDeDatos = crearProveedorDeDatos(listado); 
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }
        
    }public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<ConfiguracionLiquidacion>() {            
            @Override
            public Object getValueAt(ConfiguracionLiquidacion listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "Código":{
                       return listado1.getCodigo();
                    }
                    case "CargoNomina":{
                       return listado1.getCargoNomina().getDescripcion();
                    }
                    case "Fecha_Inicio":{
                       return listado1.getFechaInicio();
                    }
                    case "Fecha_Fin":{
                       return listado1.getFechaFinalizacion();
                    }
                    case "Cantidad_Días":{
                       return listado1.getCantidadDias();
                    }
                    case "Descripción":{
                       return listado1.getDescripcion();
                    }
                    case "Valor_Tonelada":{
                       return listado1.getValorTonelada();
                    }
                    case "Cantiddad_Tonelada_Salario":{
                       return listado1.getCantidadToneladaSalario();
                    }
                    case "Usuario":{
                       return listado1.getUsuario().getNombres()+ " "+ listado1.getUsuario().getApellidos();
                    }
                    case "Estado":{
                       return listado1.getEstado();
                    }
               }
               return null;
            }

            @Override
            public String getColumnName(int columnas) {
                return encabezadoTabla.get(columnas);
            }

            @Override
            public int getColumnCount() {
                return encabezadoTabla.size();
            }

        }; 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        paginadorDeTabla.eventCombobBox(pageJComboBox);
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        paginadorDeTabla.refreshPageButtonPanel();
    }
    public void validar(){
        encabezadoTabla= new ArrayList<>();
        encabezadoTabla.add("Código");
        encabezadoTabla.add("CargoNomina");
        encabezadoTabla.add("Fecha_Inicio");
        encabezadoTabla.add("Fecha_Fin");
        encabezadoTabla.add("Cantidad_Días");
        encabezadoTabla.add("Descripción");
        encabezadoTabla.add("Valor_Tonelada");
        encabezadoTabla.add("Cantiddad_Tonelada_Salario");
        encabezadoTabla.add("Usuario");
        encabezadoTabla.add("Estado");
        
    }
    private ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion> crearProveedorDeDatos(final ArrayList<ConfiguracionLiquidacion> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<ConfiguracionLiquidacion> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
