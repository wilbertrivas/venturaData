/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewPrincipal;

import Sistema.View.EncriptarPassword;
import javax.swing.JOptionPane;

/**
 *
 * @author wrivas
 */
public class TestBorrar extends javax.swing.JFrame {

    /**
     * Creates new form TestBorrar
     */
    public TestBorrar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        md5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sha1 = new javax.swing.JLabel();
        sha11 = new javax.swing.JTextField();
        md51 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 240, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jLabel1.setText("MD5");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        md5.setText("jLabel2");
        getContentPane().add(md5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel2.setText("sha1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        sha1.setText("jLabel2");
        getContentPane().add(sha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));
        getContentPane().add(sha11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 400, 30));

        md51.setText("jTextField2");
        getContentPane().add(md51, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 400, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(validarComplejidadContrase??a(jTextField1.getText())){
            md51.setText("contrase??a valida");
        }else{
            md51.setText("contrase??a invalida");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TestBorrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestBorrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestBorrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestBorrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestBorrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel md5;
    private javax.swing.JTextField md51;
    private javax.swing.JLabel sha1;
    private javax.swing.JTextField sha11;
    // End of variables declaration//GEN-END:variables
    
    public boolean validarComplejidadContrase??a(String clave){
        boolean numero=false;
        boolean mayuscula=false;
        boolean caracterEspecial=false;
        boolean miniscula=false;
        boolean espacio=false;
        int logitudClave=0;
       // int Arroba = 0;                     // solo la arroba -.-!
         for (int x = 0; x < clave.length(); x++) {
            if ((clave.charAt(x) >= 48 && clave.charAt(x) <= 57) //numeros
                    || (clave.charAt(x) >= 65 && clave.charAt(x) <= 90) //mayusculas
                    || (clave.charAt(x) >= 33 && clave.charAt(x) <= 47) //signos
                    || (clave.charAt(x) >= 97 && clave.charAt(x) <= 122)) {  //minusculas
            } 
            if (clave.charAt(x) >= 48 && clave.charAt(x) <= 57) { // validamos que exista un n??mero
                numero=true;
            }
            if (clave.charAt(x) >= 65 && clave.charAt(x) <= 90) { // Valida que exista una mayuscula
                mayuscula=true;
            }
            if (clave.charAt(x) >= 33 && clave.charAt(x) <= 47) { // valida que exista un caracter especial
                caracterEspecial=true;
            }
            if (clave.charAt(x) >= 97 && clave.charAt(x) <= 122) { // valida que exista una letra minuscula
                miniscula=true;
            }
            if(clave.charAt(x) == 32){
                espacio=true;
            }
            logitudClave += 1;
        } 
        boolean validar= false;
        boolean retorno=false;
        String mensaje="";
        if(!numero){
            mensaje += ", n??mero";
            validar= true;
        }
        if(!mayuscula){
            mensaje += ", mayusculas";
            validar= true;
        }
        if(!caracterEspecial){
            mensaje += " , caracteres especiales";
            validar= true;
        }
        if(!miniscula){
            mensaje += ", minisculas";
            validar= true;
        }
        if(logitudClave < 8){
           JOptionPane.showMessageDialog(null, "La contrase??a debe de tener una longitud entre 8 a 15 caracteres", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
           retorno=false;
        }else{
            if(espacio){
                JOptionPane.showMessageDialog(null, "La contrase??a no puede contener espacios", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                retorno=false;
            }else{
                if(validar){
                    JOptionPane.showMessageDialog(null, "La contrase??a debe de tener "+mensaje, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    retorno=false;
                }else{
                    retorno=true;
                }
            }
        }
        return retorno; 
    }



}
