
package ViewPrincipal;

public class Panel_Informativo extends javax.swing.JPanel {

    public Panel_Informativo(String p) {
        initComponents();
        userOnline.setText(p);   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entradaAlmacenNo1 = new javax.swing.JLabel();
        userOnline = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        entradaAlmacenNo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        entradaAlmacenNo1.setForeground(new java.awt.Color(0, 153, 51));
        entradaAlmacenNo1.setText("Usuario Conectado: ");
        add(entradaAlmacenNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        userOnline.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userOnline.setForeground(new java.awt.Color(0, 153, 153));
        userOnline.setText("Cedula:");
        add(userOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 1060, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel entradaAlmacenNo1;
    private javax.swing.JLabel userOnline;
    // End of variables declaration//GEN-END:variables
}