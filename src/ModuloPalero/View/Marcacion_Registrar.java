package ModuloPalero.View;

import Catalogo.Model.BaseDatos;
import Catalogo.Model.TipoArticulo;
import ModuloPalero.Controller.ControlDB_Marcacion;
import ModuloPalero.Model.MarcacionArchivo;
import ModuloPalero.Model.MarcacionPersona;
import ModuloPersonal.Controller.ControlDB_Persona;
import ModuloPersonal.Model.CargoNomina;
import ModuloPersonal.Model.Persona;
import ModuloPersonal.Model.TipoDocumento;
import Sistema.Model.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public final class Marcacion_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<TipoArticulo> listadoTipoArticulo = new ArrayList();
    ArrayList<BaseDatos> listadoBaseDatos = new ArrayList();
    ArrayList<MarcacionPersona> listadoMarcacion;
    private boolean validarSubirArchivo = true;

    public Marcacion_Registrar(Usuario us, String tipoConexion) {
        initComponents();
        user = us;
        this.tipoConexion = tipoConexion;
        listadoMarcacion = null;
        codigo.setText(user.getCodigo());
        nombre.setText(user.getNombres()+ " "+ user.getApellidos());
        correo.setText(user.getCorreo());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        titulo3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        codigo = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        correo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        titulo4 = new javax.swing.JLabel();
        titulo6 = new javax.swing.JLabel();
        titulo1 = new javax.swing.JLabel();
        titulo2 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        titulo5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/subirMarcacion.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 60, 60));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1430, 370));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        jButton2.setText("REGISTRAR MARCACIÓN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 250, 40));

        titulo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo3.setForeground(new java.awt.Color(0, 153, 153));
        titulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo3.setText("CARGAR ARCHIVO DE MARCACIÓN");
        titulo3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 300, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("RECUERDE QUE EL ARCHIVO A SUBIR DEBE TENER EXTENSIÓN .CSV");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 420, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("CORREO:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 60, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/alert2.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 70, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("NOMBRE:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 60, 20));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codigo.setForeground(new java.awt.Color(0, 102, 102));
        codigo.setText(".............................................................................................................");
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 440, 20));

        nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombre.setForeground(new java.awt.Color(0, 102, 102));
        nombre.setText(".............................................................................................................");
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 440, 20));

        correo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        correo.setForeground(new java.awt.Color(0, 102, 102));
        correo.setText(".............................................................................................................");
        add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 440, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("CÓDIGO:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 60, 20));

        titulo4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo4.setForeground(new java.awt.Color(0, 153, 153));
        titulo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo4.setText("USUARIO QUIEN SUBIRÁ LA MARCACIÓN");
        titulo4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 540, 30));

        titulo6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo6.setForeground(new java.awt.Color(0, 153, 153));
        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 890, 30));

        titulo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo1.setForeground(new java.awt.Color(0, 153, 153));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("SUBIR MARCACIÓN");
        titulo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1430, 30));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo2.setForeground(new java.awt.Color(0, 153, 153));
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 300, 130));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 153));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 540, 100));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1430, 130));

        titulo5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo5.setForeground(new java.awt.Color(0, 153, 153));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 890, 130));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "Recuerde que el archivo a cargar debe tener extensión .csv","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        
        listadoMarcacion = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV FILE", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: "
                    + chooser.getSelectedFile().getPath());
        }
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(chooser.getSelectedFile().getPath()));

            // Leer una linea del archivo
            String lineaValidarPersona = bufferLectura.readLine();
            lineaValidarPersona = bufferLectura.readLine();
            boolean validarExistenciaPersona = true;
            while (lineaValidarPersona != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = lineaValidarPersona.split(";");
                Persona ps = new Persona();
                ps.setCodigo(campos[0]);
                System.out.println(campos[0]);
                ps.setTipoDocumento(new TipoDocumento("1", "CEDULA", ""));
                if (!new ControlDB_Persona(tipoConexion).validarExistencia(ps)) {
                    validarExistenciaPersona = false;
                }
                //System.out.println(Arrays.toString(campos));

                // Volver a leer otra línea del fichero
                lineaValidarPersona = bufferLectura.readLine();
            }
            if (validarExistenciaPersona) {//Todas las personas que estan en el archivo se encuentran registrado en la base de datos
                //Procedemos a registrar los datos en el sistema
                bufferLectura = new BufferedReader(new FileReader(chooser.getSelectedFile().getPath()));
                String lineas = bufferLectura.readLine();
                lineas = bufferLectura.readLine();
                boolean inicializador = true;
                while (lineas != null) {
                    if (inicializador) {
                        listadoMarcacion = new ArrayList<>();
                        inicializador = false;
                    }
                    String[] camposPorLineas = lineas.split(";");
                    Persona persona = new Persona();
                        persona.setCodigo(camposPorLineas[0]);
                        persona.setTipoDocumento(new TipoDocumento("1", "CEDULA", ""));
                        persona.setNombre(camposPorLineas[1]);
                        CargoNomina cn = new CargoNomina();
                            cn.setDescripcion(camposPorLineas[2]);
                        persona.setCargoNomina(cn);
                    MarcacionPersona mar = new MarcacionPersona();
                        mar.setPersona(persona);
                    //Validamos la fecha de inicio
                    try {
                        if (!camposPorLineas[3].equals("")) {
                            mar.setFechaInicio(formatearFecha(camposPorLineas[3]));
                        } else {
                            mar.setFechaInicio(null);
                        }
                    } catch (Exception e) {
                        mar.setFechaInicio(null);
                    }
                    //Validamos la hora de inicio
                    try {
                        if(!camposPorLineas[4].equals("")){
                            mar.setHoraInicio(camposPorLineas[4]);
                        }else{
                            mar.setHoraInicio(null);
                        }
                    } catch (Exception e) {
                        mar.setHoraInicio(null);
                    }
                    //Validamos la fecha de finalización
                    try {
                        if (!camposPorLineas[5].equals("")) {
                            mar.setFechaFin(formatearFecha(camposPorLineas[5]));
                        } else {
                            mar.setFechaFin(null);
                        }
                    } catch (Exception e) {
                        mar.setFechaFin(null);
                    }
                    //Validamos la hora de finalización
                    try {
                        if(!camposPorLineas[6].equals("")){
                            mar.setHoraFin(camposPorLineas[6]);
                        }else{
                            mar.setHoraFin(null);
                        }
                    } catch (Exception e) {
                        mar.setHoraFin(null);
                    }
                    
                    mar.setEstado("1");
                    listadoMarcacion.add(mar);
                    lineas = bufferLectura.readLine();
                }
                try {
                    tabla_Listar();
                } catch (SQLException ex) {
                    Logger.getLogger(Marcacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error!!, no se puede subir la marcación debido que hay personal que no está registrado en el sistema,"
                        + " valide información", "Error!!", JOptionPane.ERROR_MESSAGE);
                validarSubirArchivo = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (validarSubirArchivo && listadoMarcacion != null) {
            try {
                //Hay un listado de marcación y las personas cargadas se encuentran registrado en el sistema.
                MarcacionArchivo marArchivo = new MarcacionArchivo();
                marArchivo.setUsuario(user);
                marArchivo.setListadoMarcacionPersona(listadoMarcacion);
                marArchivo.setEstado("1");
                int result = new ControlDB_Marcacion(tipoConexion).registrar(marArchivo);
                if (result == 1) {
                    listadoMarcacion = null;
                    //Eliminar todos los elementos de una tabla 
                    DefaultTableModel md =(DefaultTableModel)tabla.getModel();
                    int CantEliminar= tabla.getRowCount() -1;
                    for(int i =CantEliminar; i>=0; i--){
                            md.removeRow(i);
                    }
                    JOptionPane.showMessageDialog(null, "Se subió la marcación de forma exitosa.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar la marcación", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Marcacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Marcacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Marcacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error!!, no se puede subir la marcación porque contiene errores", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel codigo;
    private javax.swing.JLabel correo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo3;
    private javax.swing.JLabel titulo4;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"#", "Cedula", "Nombre completo", "Cargo", "Fecha_Incio", "Hora_Inicio", "Fecha_Fin", "Hora_Fin"});
        //listadoMarcacion=new ControlDB_Articulo(tipoConexion).buscar(valorConsulta);
        int contador = 1;
        if (listadoMarcacion != null) {
            for (MarcacionPersona listado1 : listadoMarcacion) {
                String[] registro = new String[8];
                registro[0] = "" + contador;
                registro[1] = "" + listado1.getPersona().getCodigo();
                registro[2] = "" + listado1.getPersona().getNombre();
                registro[3] = "" + listado1.getPersona().getCargoNomina().getDescripcion();
                registro[4] = "" + listado1.getFechaInicio();
                registro[5] = "" + listado1.getHoraInicio();
                registro[6] = "" + listado1.getFechaFin();
                registro[7] = "" + listado1.getHoraFin();
                modelo.addRow(registro);
                contador++;
            }
            tabla.setModel(modelo);
        }
    }
    
    public String formatearFecha(String fecha) {
        String fechaRetorno = "";
        String[] dateGuion = fecha.split("-");   //'01/07/2021'.split("-")
        String[] dateSlash = fecha.split("/");
        if (dateGuion.length == 3) {
            if (dateGuion[2].length() > 2) {
                fechaRetorno = dateGuion[2] + "-" + dateGuion[1] + "-" + dateGuion[0];  //Formato  AÑO-MES-DIA
            } else {
                if (dateGuion[0].length() > 2) {
                    fechaRetorno = dateGuion[0] + "-" + dateGuion[1] + "-" + dateGuion[2];  //Formato  AÑO-MES-DIA 
                }
            }
        } else {
            if (dateSlash.length == 3) {
                if (dateSlash[2].length() > 2) {
                    fechaRetorno = dateSlash[2] + "-" + dateSlash[1] + "-" + dateSlash[0];  //Formato  AÑO-MES-DIA
                } else {
                    if (dateSlash[0].length() > 2) {
                        fechaRetorno = dateSlash[0] + "-" + dateSlash[1] + "-" + dateSlash[2];  //Formato  AÑO-MES-DIA 
                    }
                }
            }
        }
        return fechaRetorno;
    }

}
