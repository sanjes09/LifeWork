package Vista.Recorrer;
import Controlador.Ctrl_Visitante;
import java.util.*;
import Modelo.Tour.Tour;
import javax.swing.DefaultListModel;
import Modelo.PI.*;

public class IConsultarTrDisp extends javax.swing.JFrame {
    
    Ctrl_Visitante admin= Ctrl_Visitante.instance();
    ArrayList<Tour> tours = new ArrayList();
    Tour tSelect;
    
    public IConsultarTrDisp() {
        initComponents();
        tours= admin.getToursDisponibles();
        DefaultListModel<String> agg = new DefaultListModel<>();
        for(Tour x : tours){
          agg.addElement(String.valueOf(x.getID())+"-"+x.getNombre());
        }
        jListTourDisp.setModel(agg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneUbicacion = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();
        jButtonRecorrer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTourDisp = new javax.swing.JList<>();
        jButtonSeleccionar = new javax.swing.JButton();
        jLabelNotificacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Consultar Tours Disponibles");

        jScrollPane2.setViewportView(jTextPaneUbicacion);

        jLabel1.setText("Ubicación del punto de interés inicial");

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        jButtonRecorrer.setText("Recorrer");
        jButtonRecorrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecorrerActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListTourDisp);

        jButtonSeleccionar.setText("Seleccionar");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSeleccionar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSalir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                                .addComponent(jButtonRecorrer))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabelNotificacion)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRecorrer)
                    .addComponent(jButtonSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNotificacion)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRecorrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecorrerActionPerformed
        if(tSelect==null) jLabelNotificacion.setText("Seleccione un tour primero");
        else{
            this.dispose();
            admin.recorrer(tSelect);
        }
    }//GEN-LAST:event_jButtonRecorrerActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
        admin.visitante();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
        String s;
        String []x;
        int aux=0;
        ConjuntoPI j=admin.getPI();
        s=jListTourDisp.getSelectedValue();
        x=s.split("-");
        for(Tour t : tours){
            if(t.getID()==Integer.parseInt(x[0])){
               aux= t.getPInicial();
               tSelect=t;
               break;
            }   
        }
        jTextPaneUbicacion.setText(j.getPI(aux).get_Ubicacion());
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRecorrer;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelNotificacion;
    private javax.swing.JList<String> jListTourDisp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneUbicacion;
    // End of variables declaration//GEN-END:variables
}
