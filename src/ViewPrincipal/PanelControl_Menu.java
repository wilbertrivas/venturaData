
package ViewPrincipal;

import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.View.MvtoEquipo_ModificarFinal;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class PanelControl_Menu extends javax.swing.JPanel {

    private String tipoConexion ="";
    private ArrayList<CentroOperacion> listadoCentroOperacion = null;
    
    public PanelControl_Menu(String p, String tipoConexion) {
        initComponents();
        userOnline.setText(p);   
        this.tipoConexion=tipoConexion;

        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            //listadoCentroOperacion_mvtoEquipo=listadoCentroOperacion_mvtoCarbon;
            if(listadoCentroOperacion != null){  
                String datosObjeto[]= new String[listadoCentroOperacion.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_CentroOperacion.setModel(model);
                //select_MvtoEquipo_CO.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }        
        InternalFrameMenu.getContentPane().setBackground(Color.WHITE);
        InternalFrameMenu.show(false); 
        
        //Cargamos la interfaz de graficadora
         panel.setViewportView(new Panel_GraficarHistoricoRecuadoPorLavado(tipoConexion, listadoCentroOperacion.get(select_CentroOperacion.getSelectedIndex()),true));
        //Ocultamos el panel de MENU
        //InternalFrameMenu.show(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InternalFrameMenu = new javax.swing.JInternalFrame();
        jButton2 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        userOnline = new javax.swing.JLabel();
        entradaAlmacenNo2 = new javax.swing.JLabel();
        entradaAlmacenNo1 = new javax.swing.JLabel();
        titulo31 = new javax.swing.JLabel();
        select_CentroOperacion = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        panel = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrameMenu.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameMenu.setClosable(true);
        InternalFrameMenu.setVisible(false);
        InternalFrameMenu.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        jButton2.setText("RECAUDO POR LAVADO VEHÍCULOS");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameMenu.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 610, 40));

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        jButton17.setText("HISTORICO VEHÍCULOS DESCARGADOS");
        jButton17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        InternalFrameMenu.getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 470, 40));

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        jButton18.setText("HISTORICO TONELADAS DESCARGADAS");
        jButton18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        InternalFrameMenu.getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 470, 40));

        jButton19.setBackground(new java.awt.Color(255, 255, 255));
        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        jButton19.setText("HISTORICO VEHÍCULOS DESCARGADOS POR AUXILIAR");
        jButton19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        InternalFrameMenu.getContentPane().add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 470, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("SELECCIONE EL TIPO DE CONSULTA QUE DESEA REALIZAR:");
        InternalFrameMenu.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 0, 550, -1));

        jButton20.setBackground(new java.awt.Color(255, 255, 255));
        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        jButton20.setText("HISTORICO GRAFICO VEHÍCULOS DESCARGADOS VENTURADATA VS CONTROLCARGA GP");
        jButton20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        InternalFrameMenu.getContentPane().add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 610, 40));

        jButton21.setBackground(new java.awt.Color(255, 255, 255));
        jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        jButton21.setText("VEHÍCULO DESCARGADOS VENTURADATA VS CONTROLCARGA GP");
        jButton21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        InternalFrameMenu.getContentPane().add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 610, 40));

        add(InternalFrameMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 190));

        userOnline.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userOnline.setForeground(new java.awt.Color(0, 153, 153));
        userOnline.setText("Cedula:");
        add(userOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 1060, -1));

        entradaAlmacenNo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        entradaAlmacenNo2.setForeground(new java.awt.Color(0, 153, 51));
        entradaAlmacenNo2.setText("Usuario Conectado: ");
        add(entradaAlmacenNo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 130, -1));

        entradaAlmacenNo1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        entradaAlmacenNo1.setForeground(new java.awt.Color(0, 102, 102));
        entradaAlmacenNo1.setText("PANEL DE CONTROL");
        add(entradaAlmacenNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 18, 360, 30));

        titulo31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo31.setForeground(new java.awt.Color(51, 51, 51));
        titulo31.setText("CENTRO OPERACIÓN:");
        titulo31.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo31, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 140, 30));

        select_CentroOperacion.setToolTipText("");
        select_CentroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_CentroOperacionItemStateChanged(evt);
            }
        });
        add(select_CentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 260, 30));

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesar.png"))); // NOI18N
        jButton16.setText("Seleccionar tipo de Consulta");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 250, 30));
        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1570, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void select_CentroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_CentroOperacionItemStateChanged
    }//GEN-LAST:event_select_CentroOperacionItemStateChanged

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        InternalFrameMenu.show(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        //Cargamos la interfaz de graficadora
        panel.setViewportView(new Panel_GraficarHistoricoVehículosDescargados(tipoConexion, listadoCentroOperacion.get(select_CentroOperacion.getSelectedIndex())));
        //Ocultamos el panel de MENU
        InternalFrameMenu.show(false);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        //Cargamos la interfaz de graficadora
        panel.setViewportView(new Panel_GraficarHistoricoToneladasDescargadas(tipoConexion, listadoCentroOperacion.get(select_CentroOperacion.getSelectedIndex())));
        //Ocultamos el panel de MENU
        InternalFrameMenu.show(false);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        //Cargamos la interfaz de graficadora
        panel.setViewportView(new Panel_GraficarHistoricoVehículosDescargadosPorAuxiliar(tipoConexion, listadoCentroOperacion.get(select_CentroOperacion.getSelectedIndex())));
        //Ocultamos el panel de MENU
        InternalFrameMenu.show(false);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Cargamos la interfaz de graficadora
        panel.setViewportView(new Panel_GraficarHistoricoRecuadoPorLavado(tipoConexion, listadoCentroOperacion.get(select_CentroOperacion.getSelectedIndex()), false));
        //Ocultamos el panel de MENU
        InternalFrameMenu.show(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //Cargamos la interfaz de graficadora
        panel.setViewportView(new Panel_GraficarHistoricoVehículosDescargadosVenturaDataVSControlCargaGP(tipoConexion, listadoCentroOperacion.get(select_CentroOperacion.getSelectedIndex())));
        //Ocultamos el panel de MENU
        InternalFrameMenu.show(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        panel.setViewportView(new MvtoCarbon_ConsultaVehiculosDescControlCargaVSVenturaData2(tipoConexion));
        //Ocultamos el panel de MENU
        InternalFrameMenu.show(false);
    }//GEN-LAST:event_jButton21ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternalFrameMenu;
    private javax.swing.JLabel entradaAlmacenNo1;
    private javax.swing.JLabel entradaAlmacenNo2;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane panel;
    private javax.swing.JComboBox<String> select_CentroOperacion;
    private javax.swing.JLabel titulo31;
    private javax.swing.JLabel userOnline;
    // End of variables declaration//GEN-END:variables
}
