package Catalogo.View;
  
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class CentroCostoSubCentro_Registrar extends javax.swing.JPanel {
    
    Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    
    public CentroCostoSubCentro_Registrar(Usuario us,String tipoConexion) {
        user=us;
        this.tipoConexion= tipoConexion;
        initComponents();
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            if(listadoCentroOperacion != null){
                String datosObjeto[]= new String[listadoCentroOperacion.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                centroOperacion.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los SubCentros de Costos");
            Logger.getLogger(CentroCostoSubCentro_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        alerta_nombre = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        titulo28 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        requiereLaborRealizada = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 330, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 140, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("LISTADO DE SUBCENTRO DE COSTOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 420, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("CentroCosto x LaborRealizada:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 230, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 330, 30));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 150, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 150, 40));

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
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 710, 640));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 460, 20));

        centroOperacion.setToolTipText("");
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 330, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 140, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRO DE SUBCENTROS DE COSTOS");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 630, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Estado:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 140, 30));

        requiereLaborRealizada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        requiereLaborRealizada.setToolTipText("");
        add(requiereLaborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 330, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(nombre.getText().equals("")){
            alerta_nombre.setText("El nombre del Subcentro de costo no puede estar vacio");
        }else{
            CentroCostoSubCentro Objeto = new CentroCostoSubCentro();
            Objeto.setDescripcion(nombre.getText());
            //Validamos si selecciono activo o inactivo
            if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                Objeto.setEstado("1");
            }else{
                Objeto.setEstado("0");
            }
            if(requiereLaborRealizada.getSelectedItem().toString().equalsIgnoreCase("NO")){
                Objeto.setCentroCostoRequiereLaborRealizada("0");
            }else{
                Objeto.setCentroCostoRequiereLaborRealizada("1");
            }
            try{
                if(listadoCentroOperacion != null){
                    Objeto.setCentroOperacion(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                    try {
                        if(new ControlDB_CentroCostoSubCentro(tipoConexion).validarExistencia(Objeto)){
                            JOptionPane.showMessageDialog(null, "El Sub centro de Costo ya se encuentra registrado en el sistema");
                        }else{
                            int respuesta=new ControlDB_CentroCostoSubCentro(tipoConexion).registrar(Objeto,user);
                            if(respuesta==1){
                                JOptionPane.showMessageDialog(null, "Se registro el Sub Centro de Costo de Forma Exitosa");
                                nombre.setText("");
                                estado.setSelectedIndex(0);
                                tabla_Listar("");
                            }else{
                                if(respuesta==0){
                                    JOptionPane.showMessageDialog(null, "No se pudo registrar el Sub Centro de Costo, valide datos");
                                }
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el Sub Centro de Costo");
                        Logger.getLogger(CentroCostoSubCentro_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CentroCostoSubCentro_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(CentroCostoSubCentro_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(CentroCostoSubCentro_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Error debe cargar un Centro de Costo");
                }
            }catch(Exception e){
            
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        nombre.setText("");
        requiereLaborRealizada.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> requiereLaborRealizada;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo28;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código","Centro Operación","Nombre","CentroCosto_RequiereActividad","Estado"});  
        ArrayList<CentroCostoSubCentro> listado=new ControlDB_CentroCostoSubCentro(tipoConexion).buscar(valorConsulta);
        for (CentroCostoSubCentro listado1 : listado) {
            String[] registro = new String[5];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getCentroOperacion().getDescripcion();
            registro[2] = "" + listado1.getDescripcion();
            if(listado1.getCentroCostoRequiereLaborRealizada().equals("1")){
                registro[3] = "SI";
            }else{
                registro[3] = "NO";
            }
            registro[4] = "" + listado1.getEstado();
            modelo.addRow(registro);      
        }
        
        tabla.setModel(modelo);
    }

}
