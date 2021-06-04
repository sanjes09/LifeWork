package Vista.Recorrer;
import Controlador.Ctrl_Visitante;

public class IAcceso_Visitante extends javax.swing.JFrame {
    
    Ctrl_Visitante admin= Ctrl_Visitante.instance();

    public IAcceso_Visitante() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonConsTDisp = new javax.swing.JButton();
        jButtonRecorrer = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED - Visitante");

        jButtonConsTDisp.setText("Consultar Tours Disponibles");
        jButtonConsTDisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsTDispActionPerformed(evt);
            }
        });

        jButtonRecorrer.setText("Recorrer Tour");
        jButtonRecorrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecorrerActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButtonConsTDisp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jButtonRecorrer)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsTDisp)
                    .addComponent(jButtonRecorrer))
                .addGap(18, 18, 18)
                .addComponent(jButtonSalir)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConsTDispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsTDispActionPerformed
        this.dispose();  
        admin.Consultar();
    }//GEN-LAST:event_jButtonConsTDispActionPerformed

    private void jButtonRecorrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecorrerActionPerformed
        this.dispose(); 
        admin.Consultar();
    }//GEN-LAST:event_jButtonRecorrerActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
        admin.salir();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConsTDisp;
    private javax.swing.JButton jButtonRecorrer;
    private javax.swing.JButton jButtonSalir;
    // End of variables declaration//GEN-END:variables
}
