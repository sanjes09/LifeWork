package Vista.Tour;

import Controlador.Ctrl_AdminTour;
import Data.Data;
import Modelo.PI.PuntoInteres;
import Modelo.Tour.Tour;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class IEliminarTour extends javax.swing.JFrame {
    
    Ctrl_AdminTour admin = Ctrl_AdminTour.instance();
    ArrayList<PuntoInteres> cpi=Data.conjuntoPI.consultarTodo();
    ArrayList<Tour> tours = admin.getTours();
    Tour tour;
    
    public IEliminarTour() {
        initComponents();
        
        int c=0;
        String x="...";
        jComboBoxTours.addItem(x);
        if(tours!=null){
            for(Tour t : tours){
                x=String.valueOf(t.getID())+"-"+String.valueOf(t.getNombre());
                c++;
                jComboBoxTours.addItem(x);
            }
        }
        if(c==0){
            jLabelNotificacion.setText("No hay tours");
        }
        else jLabelNotificacion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneIdentificador = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneNombre = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPaneDisponibilidad = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPanePInicial = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextPanePFinal = new javax.swing.JTextPane();
        jButtonCancelar = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();
        jComboBoxTours = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPIntermedios = new javax.swing.JList<>();
        jLabelNotificacion = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane6.setViewportView(jTextArea1);

        jScrollPane8.setViewportView(jTextPane6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Eliminar Tour");

        jLabel1.setText("Tours:");

        jLabel2.setText("Identificador:");

        jScrollPane2.setViewportView(jTextPaneIdentificador);

        jLabel3.setText("Nombre:");

        jScrollPane3.setViewportView(jTextPaneNombre);

        jLabel4.setText("Disponibilidad:");

        jScrollPane4.setViewportView(jTextPaneDisponibilidad);

        jLabel5.setText("Puntos de interés inicial");

        jScrollPane5.setViewportView(jTextPanePInicial);

        jLabel6.setText("Puntos de interés Intermedios");

        jLabel7.setText("Punto de interés final");

        jScrollPane9.setViewportView(jTextPanePFinal);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jComboBoxTours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxToursActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListPIntermedios);

        jLabelNotificacion.setText("Notificacion:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNotificacion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButtonConfirmar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                                    .addComponent(jButtonCancelar))
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane5)
                                .addComponent(jScrollPane9)
                                .addComponent(jComboBoxTours, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)))
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelNotificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonConfirmar))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
        admin.admin_tour();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        admin.confirmarElim(tour);
        this.dispose();
        admin.admin_tour();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jComboBoxToursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxToursActionPerformed
        String y;
        String[] j;
        DefaultListModel<String> agregar = new DefaultListModel<>();
        y = jComboBoxTours.getSelectedItem().toString();
        j = y.split("-");
        y = j[0];
        String x;
        if(tours!=null){
            for(Tour t1 : tours){
                x=String.valueOf(t1.getID());
                if(x.compareTo(y)==0){  
                    tour=t1;
                    jTextPaneDisponibilidad.setText(String.valueOf(t1.getDips()));
                    jTextPaneIdentificador.setText(String.valueOf(t1.getID()));
                    jTextPaneNombre.setText(t1.getNombre());
                    for(PuntoInteres pi : cpi){
                        if(pi.get_Coordenada()==t1.getPInicial()){
                            jTextPanePInicial.setText(String.valueOf(pi.get_Coordenada())+"-"+pi.get_Ubicacion());
                        }
                        if(pi.get_Coordenada()==t1.getPFinal()){
                            jTextPanePFinal.setText(String.valueOf(pi.get_Coordenada())+"-"+pi.get_Ubicacion());
                        }
                        if(t1.getPIntermedios()!=null){
                            for(Integer coordenada : t1.getPIntermedios()){
                                if(pi.get_Coordenada()==coordenada){
                                   agregar.addElement(String.valueOf(pi.get_Coordenada())+"-"+pi.get_Ubicacion());
                                }
                            }
                        }
                    }
                    jListPIntermedios.setModel(agregar);
                    break;
                }
            }
        }
    }//GEN-LAST:event_jComboBoxToursActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JComboBox<String> jComboBoxTours;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelNotificacion;
    private javax.swing.JList<String> jListPIntermedios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPaneDisponibilidad;
    private javax.swing.JTextPane jTextPaneIdentificador;
    private javax.swing.JTextPane jTextPaneNombre;
    private javax.swing.JTextPane jTextPanePFinal;
    private javax.swing.JTextPane jTextPanePInicial;
    // End of variables declaration//GEN-END:variables
}
