package ModuloPalero.View;

import ModuloPalero.Controller.ControlDB_Marcacion;
import ModuloPalero.Model.MarcacionArchivo;
import ModuloPalero.Model.MarcacionPersona;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;

public final class Marcacion_Consultar extends javax.swing.JPanel implements ActionListener, TableModelListener {

    ArrayList<MarcacionArchivo> listado = null;
    private final Usuario user;
    private final String tipoConexion;
    private PaginadorDeTabla<MarcacionArchivo> paginadorDeTabla;
    ProveedorDeDatosDePaginacion<MarcacionArchivo> proveedorDeDatos;
    ArrayList<String> encabezadoTabla = null;

    public Marcacion_Consultar(Usuario user, String tipoConexion) {
        initComponents();
        this.user = user;
        this.tipoConexion = tipoConexion;
        for (int i = 0; i <= 23; i++) {
            if (i <= 9) {
                horaInicio.addItem("0" + i);
                horaFin.addItem("0" + i);
            } else {
                horaInicio.addItem("" + i);
                horaFin.addItem("" + i);
            }
        }
        for (int i = 0; i <= 59; i++) {
            if (i <= 9) {
                minutoInicio.addItem("0" + i);
                minutoFin.addItem("0" + i);
            } else {
                minutoInicio.addItem("" + i);
                minutoFin.addItem("" + i);
            }
        }
        Dimension dim = super.getToolkit().getScreenSize();
        encabezadoTabla = new ArrayList<String>();
        pageJComboBox.show(false);
        // InternalFrame_listadoPersonas.show(false);
        InternalFrame_listadoPersonas.getContentPane().setBackground(Color.WHITE);
        InternalFrame_listadoPersonas.show(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        InternalFrame_listadoPersonas = new javax.swing.JInternalFrame();
        fechaSubidaArchivo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_listadoPersonasMarcacion = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        titulo66 = new javax.swing.JLabel();
        titulo35 = new javax.swing.JLabel();
        titulo36 = new javax.swing.JLabel();
        titulo37 = new javax.swing.JLabel();
        titulo38 = new javax.swing.JLabel();
        consecutivo = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        estado = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        consultar = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        minutoInicio = new javax.swing.JComboBox<>();
        horaInicio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        minutoFin = new javax.swing.JComboBox<>();
        horaFin = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        alerta_fechaFinal = new javax.swing.JLabel();
        alerta_fechaInicio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        horarioTiempoIFinalMvtoCarbon_Procesar = new javax.swing.JLabel();
        horarioTiempoInicioMvtoCarbon_Procesar = new javax.swing.JLabel();
        paginationPanel = new javax.swing.JPanel();
        pageJComboBox = new javax.swing.JComboBox<>();

        jMenuItem1.setText("Seleccionar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrame_listadoPersonas.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrame_listadoPersonas.setClosable(true);
        InternalFrame_listadoPersonas.setVisible(false);
        InternalFrame_listadoPersonas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fechaSubidaArchivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fechaSubidaArchivo.setForeground(new java.awt.Color(0, 102, 102));
        fechaSubidaArchivo.setText("......................................................................................................................................");
        InternalFrame_listadoPersonas.getContentPane().add(fechaSubidaArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 560, 30));

        tabla_listadoPersonasMarcacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_listadoPersonasMarcacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_listadoPersonasMarcacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_listadoPersonasMarcacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_listadoPersonasMarcacion);

        InternalFrame_listadoPersonas.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1280, 510));
        InternalFrame_listadoPersonas.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1260, 10));
        InternalFrame_listadoPersonas.getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 1260, 30));

        titulo66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo66.setForeground(new java.awt.Color(0, 51, 51));
        titulo66.setText("LISTADO DEL PERSONAL");
        InternalFrame_listadoPersonas.getContentPane().add(titulo66, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 450, 30));

        titulo35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo35.setForeground(new java.awt.Color(102, 102, 102));
        titulo35.setText("CONSECUTIVO:");
        InternalFrame_listadoPersonas.getContentPane().add(titulo35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 250, 30));

        titulo36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo36.setForeground(new java.awt.Color(102, 102, 102));
        titulo36.setText("USUARIO QUIEN LO SUBIÓ:");
        InternalFrame_listadoPersonas.getContentPane().add(titulo36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 250, 30));

        titulo37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo37.setForeground(new java.awt.Color(102, 102, 102));
        titulo37.setText("FECHA DE SUBIDA DEL ARCHIVO:");
        InternalFrame_listadoPersonas.getContentPane().add(titulo37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 250, 30));

        titulo38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo38.setForeground(new java.awt.Color(102, 102, 102));
        titulo38.setText("ESTADO:");
        InternalFrame_listadoPersonas.getContentPane().add(titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 250, 30));

        consecutivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consecutivo.setForeground(new java.awt.Color(0, 102, 102));
        consecutivo.setText("......................................................................................................................................");
        InternalFrame_listadoPersonas.getContentPane().add(consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 560, 30));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setForeground(new java.awt.Color(0, 102, 102));
        usuario.setText("......................................................................................................................................");
        InternalFrame_listadoPersonas.getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 560, 30));

        estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estado.setForeground(new java.awt.Color(0, 102, 102));
        estado.setText("......................................................................................................................................");
        InternalFrame_listadoPersonas.getContentPane().add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 560, 30));

        add(InternalFrame_listadoPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 750));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 153));
        titulo.setText("CONSULTAR MARCACIÓN");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 470, 30));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        tabla.setComponentPopupMenu(jPopupMenu1);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1390, 410));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/consultar.png"))); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                consultarMouseExited(evt);
            }
        });
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 210, 35));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 50, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 50, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hora");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("FECHA/HORA FIN");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("|");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 40, 70));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 170, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 50, 30));

        horaFin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaFinItemStateChanged(evt);
            }
        });
        horaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaFinActionPerformed(evt);
            }
        });
        add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Hora");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("FECHA/HORA INICIO");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 30));

        alerta_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaFinal.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 480, 20));

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1270, 10));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 20, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText(":");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 20, 30));

        horarioTiempoIFinalMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoIFinalMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 50, 30));

        horarioTiempoInicioMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoInicioMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 50, 30));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 1300, 80));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, 60, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        paginationPanel.removeAll();
        validarSeleccionCampos();
        generarListadoMvtoCarbon();
        //resizeColumnWidth(tabla);
    }//GEN-LAST:event_consultarActionPerformed

    private void fechaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseClicked
        alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicioMouseClicked

    private void fechaInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioMouseEntered

    private void minutoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoInicioActionPerformed

    private void fechaFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseClicked
        alerta_fechaFinal.setText("");
    }//GEN-LAST:event_fechaFinMouseClicked

    private void fechaFinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinMouseEntered

    private void minutoFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinActionPerformed

    private void consultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseExited
        alerta_fechaInicio.setText("");
        alerta_fechaFinal.setText("");
    }//GEN-LAST:event_consultarMouseExited

    private void horaInicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioItemStateChanged
        if (horaInicio.getSelectedIndex() <= 11) {
            horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        } else {
            horarioTiempoInicioMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaInicioItemStateChanged

    private void horaFinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFinItemStateChanged
        if (horaFin.getSelectedIndex() <= 11) {
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        } else {
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaFinItemStateChanged

    private void horaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try {
            fila1 = tabla.getSelectedRow();
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun registro");
            } else {
                int posicion = paginadorDeTabla.getPosicionador();
                if (posicion != -1) {
                    fila1 = fila1 + posicion;
                }
                if (listado != null) {
                    MarcacionArchivo marcacionArchivo2 = listado.get(fila1);
                    MarcacionArchivo marcacionArchivo = new ControlDB_Marcacion(tipoConexion).buscarArchivosMarcacionRegistros(marcacionArchivo2);
                    consecutivo.setText(marcacionArchivo.getCodigo());
                    fechaSubidaArchivo.setText(marcacionArchivo.getFecha());
                    usuario.setText(marcacionArchivo.getUsuario().getNombres() + " " + marcacionArchivo.getUsuario().getApellidos());
                    estado.setText(marcacionArchivo.getEstado());
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Item", "Cedula", "Nombre", "Cargo", "Tipo_Contrato", "Cuadrilla", "Fecha_Inicio", "Hora_Inicio", "Fecha_Fin", "Hora_Fin"});
                    int contador = 1;
                    for (MarcacionPersona objeto : marcacionArchivo.getListadoMarcacionPersona()) {
                        String[] registro = new String[10];
                        registro[0] = "" + contador;
                        registro[1] = "" + objeto.getPersona().getCodigo();
                        registro[2] = "" + objeto.getPersona().getNombre() + " " + objeto.getPersona().getApellido();
                        registro[3] = "" + objeto.getPersona().getCargoNomina().getDescripcion();
                        registro[4] = "" + objeto.getPersona().getTipoContrato().getDescripcion();
                        registro[5] = "" + objeto.getPersona().getEquipo().getDescripcion() + " " + objeto.getPersona().getEquipo().getModelo();
                        registro[6] = "" + objeto.getFechaInicio();
                        registro[7] = "" + objeto.getHoraInicio();
                        registro[8] = "" + objeto.getFechaFin();
                        registro[9] = "" + objeto.getHoraFin();
                        modelo.addRow(registro);
                        contador++;
                    }
                    tabla_listadoPersonasMarcacion.setModel(modelo);
                    InternalFrame_listadoPersonas.show(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tabla_listadoPersonasMarcacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_listadoPersonasMarcacionMouseClicked
        
    }//GEN-LAST:event_tabla_listadoPersonasMarcacionMouseClicked

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try {
                fila1 = tabla.getSelectedRow();
                if (fila1 == -1) {
                    JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun registro");
                } else {
                    int posicion = paginadorDeTabla.getPosicionador();
                    if (posicion != -1) {
                        fila1 = fila1 + posicion;
                    }
                    if (listado != null) {
                        MarcacionArchivo marcacionArchivo2 = listado.get(fila1);
                        MarcacionArchivo marcacionArchivo = new ControlDB_Marcacion(tipoConexion).buscarArchivosMarcacionRegistros(marcacionArchivo2);
                        consecutivo.setText(marcacionArchivo.getCodigo());
                        fechaSubidaArchivo.setText(marcacionArchivo.getFecha());
                        usuario.setText(marcacionArchivo.getUsuario().getNombres() + " " + marcacionArchivo.getUsuario().getApellidos());
                        estado.setText(marcacionArchivo.getEstado());
                        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Item", "Cedula", "Nombre", "Cargo", "Tipo_Contrato", "Cuadrilla", "Fecha_Inicio", "Hora_Inicio", "Fecha_Fin", "Hora_Fin"});
                        int contador = 1;
                        for (MarcacionPersona objeto : marcacionArchivo.getListadoMarcacionPersona()) {
                            String[] registro = new String[10];
                            registro[0] = "" + contador;
                            registro[1] = "" + objeto.getPersona().getCodigo();
                            registro[2] = "" + objeto.getPersona().getNombre() + " " + objeto.getPersona().getApellido();
                            registro[3] = "" + objeto.getPersona().getCargoNomina().getDescripcion();
                            registro[4] = "" + objeto.getPersona().getTipoContrato().getDescripcion();
                            registro[5] = "" + objeto.getPersona().getEquipo().getDescripcion() + " " + objeto.getPersona().getEquipo().getModelo();
                            registro[6] = "" + objeto.getFechaInicio();
                            registro[7] = "" + objeto.getHoraInicio();
                            registro[8] = "" + objeto.getFechaFin();
                            registro[9] = "" + objeto.getHoraFin();
                            modelo.addRow(registro);
                            contador++;
                        }
                        tabla_listadoPersonasMarcacion.setModel(modelo);
                        InternalFrame_listadoPersonas.show(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternalFrame_listadoPersonas;
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JLabel consecutivo;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel estado;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel fechaSubidaArchivo;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoInicioMvtoCarbon_Procesar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_listadoPersonasMarcacion;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo35;
    private javax.swing.JLabel titulo36;
    private javax.swing.JLabel titulo37;
    private javax.swing.JLabel titulo38;
    private javax.swing.JLabel titulo66;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables

    public void tabla_Listar(String data1, String data2) throws SQLException {
        tabla.setModel(crearModeloDeTabla());
        listado = new ControlDB_Marcacion(tipoConexion).buscarArchivosMarcacion(data1, data2);
        proveedorDeDatos = crearProveedorDeDatos(listado);
        paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
        paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
        pageJComboBox = paginadorDeTabla.getComboBoxPage();
        events();
        pageJComboBox.setSelectedItem(Integer.parseInt("50"));
        //resizeColumnWidth(tabla);
    }

    public void generarListadoMvtoCarbon() {
        try {
            String fechaMarcacionArchivo_Inicial = "";
            String fechaMarcacionArchivo_Final = "";
            int contador = 0;
            try {
                Calendar fechaI = fechaInicio.getCalendar();
                String anoI = "" + fechaI.get(Calendar.YEAR);
                String mesI = "";
                if ((fechaI.get(Calendar.MONTH) + 1) <= 9) {
                    mesI = "0" + (fechaI.get(Calendar.MONTH) + 1);
                } else {
                    mesI = "" + (fechaI.get(Calendar.MONTH) + 1);
                }
                String diaI = "";
                if (fechaI.get(Calendar.DAY_OF_MONTH) <= 9) {
                    diaI = "0" + fechaI.get(Calendar.DAY_OF_MONTH);
                } else {
                    diaI = "" + fechaI.get(Calendar.DAY_OF_MONTH);
                }
                fechaMarcacionArchivo_Inicial = anoI + "-" + mesI + "-" + diaI;
                contador++;
            } catch (Exception e) {
                alerta_fechaInicio.setText("Verifique la fecha de Inicio");
            }
            try {
                Calendar fechaF = fechaFin.getCalendar();
                String anoF = "" + fechaF.get(Calendar.YEAR);
                String mesF = "";
                if ((fechaF.get(Calendar.MONTH) + 1) <= 9) {
                    mesF = "0" + (fechaF.get(Calendar.MONTH) + 1);
                } else {
                    mesF = "" + (fechaF.get(Calendar.MONTH) + 1);
                }
                String diaF = "";
                if (fechaF.get(Calendar.DAY_OF_MONTH) <= 9) {
                    diaF = "0" + fechaF.get(Calendar.DAY_OF_MONTH);
                } else {
                    diaF = "" + fechaF.get(Calendar.DAY_OF_MONTH);
                }
                fechaMarcacionArchivo_Final = anoF + "-" + mesF + "-" + diaF;
                contador++;
            } catch (Exception e) {
                alerta_fechaFinal.setText("Verifique la fecha Final");
            }
            if (contador == 2) {//Se validaron las dos fechas y contienen formato correcto
                fechaMarcacionArchivo_Inicial = fechaMarcacionArchivo_Inicial + " " + horaInicio.getSelectedItem().toString() + ":" + minutoInicio.getSelectedItem().toString();
                fechaMarcacionArchivo_Final = fechaMarcacionArchivo_Final + " " + horaFin.getSelectedItem().toString() + ":" + minutoFin.getSelectedItem().toString();
                try {
                    tabla_Listar(fechaMarcacionArchivo_Inicial, fechaMarcacionArchivo_Final);
                } catch (SQLException ex) {
                    Logger.getLogger(Marcacion_Consultar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo carga los archivos de marcación", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void events() {
        pageJComboBox.addActionListener(this);
        tabla.getModel().addTableModelListener(this);
    }

    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<MarcacionArchivo>() {
            @Override
            public Object getValueAt(MarcacionArchivo listado1, int columnas) {
                switch (encabezadoTabla.get(columnas)) {
                    case "Código": {
                        return listado1.getCodigo();
                    }
                    case "Fecha": {
                        return listado1.getFecha();
                    }
                    case "Usuario_código": {
                        return listado1.getUsuario().getCodigo();
                    }
                    case "Usuario_nombre": {
                        return listado1.getUsuario().getNombres() + " " + listado1.getUsuario().getApellidos();
                    }
                    case "Estado": {
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

    private ProveedorDeDatosDePaginacion<MarcacionArchivo> crearProveedorDeDatos(final ArrayList<MarcacionArchivo> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.

        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<MarcacionArchivo>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }
            @Override
            public List<MarcacionArchivo> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }

    public void validarSeleccionCampos() {
        encabezadoTabla = new ArrayList<>();
        encabezadoTabla.add("Código");
        encabezadoTabla.add("Fecha");
        encabezadoTabla.add("Usuario_código");
        encabezadoTabla.add("Usuario_nombre");
        encabezadoTabla.add("Estado");
    }

    //Ajustar aNcho de las tablas de acuerdo al contenido
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
