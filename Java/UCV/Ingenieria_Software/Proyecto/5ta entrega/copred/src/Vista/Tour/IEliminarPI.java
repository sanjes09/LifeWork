package Vista.Tour;

import Controlador.Ctrl_AdminTour;
import Data.Data;
import Modelo.PI.PuntoInteres;
import Modelo.Tour.Tour;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class IEliminarPI extends javax.swing.JFrame {
    Ctrl_AdminTour admin = Ctrl_AdminTour.instance();
    Tour tour;
    Tour tourTemp=new Tour();
    ArrayList<PuntoInteres> cpi=Data.conjuntoPI.consultarTodo();
    DefaultListModel<String> agregar = new DefaultListModel<>();
    DefaultListModel<String> modelo = new DefaultListModel<>();
    int pin=0,pfin=0;
    IModificar imodif;
    
    public IEliminarPI(Tour t, IModificar n) {
        initComponents();
        tour=t;
        int p=0;
        imodif=n;
        
        for(PuntoInteres pi: cpi){
            p=0;
            if(tour.getPIntermedios()!=null){
                for(Integer h : tour.getPIntermedios()){
                    if(h.equals(pi.get_Coordenada())){
                        p=1;
                        break;
                    }
                }
            }
            if((tour.getPInicial()!=pi.get_Coordenada())&&(tour.getPFinal()!=pi.get_Coordenada())&&(p==1)){
                agregar.addElement(String.valueOf(pi.get_Coordenada())+"-"+pi.get_Ubicacion());
            }   
            if((tour.getPInicial()==pi.get_Coordenada())){
                jCheckBoxPFinal.setText(String.valueOf(tour.getPInicial())+"-"+pi.get_Ubicacion());
            }
            if((tour.getPFinal()==pi.get_Coordenada())){
                jCheckBoxPFinal.setText(String.valueOf(tour.getPFinal())+"-"+pi.get_Ubicacion());
            }
        }
        tourTemp.setDisp(tour.getDips());
        tourTemp.setNombre(tour.getNombre());
        tourTemp.setID(tour.getID());
        tourTemp.setPInicial(tour.getPInicial());
        tourTemp.setPFinal(tour.getPFinal());
        tourTemp.setPIntermedios(tour.getPIntermedios());
        
        jListPIntermediosDisp.setModel(agregar);
        if(tour.getPFinal()==0) jCheckBoxPFinal.setText("No tiene punto de interés final");
        
        if(tour.getPInicial()==0) jCheckBoxPInicial.setText("No tiene punto de interés inicial");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jCheckBoxPInicial = new javax.swing.JCheckBox();
        jCheckBoxPFinal = new javax.swing.JCheckBox();
        jButtonSeleccionar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListPIntermediosDisp = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListPIntermediosSelect = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabelPInicial = new javax.swing.JLabel();
        jLabelPFinal = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Eliminar Punto de interés de un Tour");

        jLabel2.setText("Puntos Intermedios");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jCheckBoxPInicial.setText("Punto Inicial");
        jCheckBoxPInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPInicialActionPerformed(evt);
            }
        });

        jCheckBoxPFinal.setText("Punto Final");
        jCheckBoxPFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPFinalActionPerformed(evt);
            }
        });

        jButtonSeleccionar.setText(">>");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });

        jButtonRemover.setText("<<");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(jListPIntermediosDisp);

        jScrollPane7.setViewportView(jListPIntermediosSelect);

        jLabel1.setText("Eliminar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButtonSeleccionar)
                                            .addComponent(jButtonRemover))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(228, 228, 228)
                                        .addComponent(jButtonConfirmar)
                                        .addGap(68, 68, 68)
                                        .addComponent(jButtonCancelar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBoxPInicial)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelPInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBoxPFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPFinal)
                        .addContainerGap(603, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPInicial)
                    .addComponent(jLabelPInicial))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButtonSeleccionar)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonRemover)
                            .addGap(38, 38, 38)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPFinal)
                    .addComponent(jLabelPFinal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonConfirmar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxPInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPInicialActionPerformed
       if(pin==1){
           pin=0;
       }else{
           pin=1;
       }
    }//GEN-LAST:event_jCheckBoxPInicialActionPerformed

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
        modelo.addElement(jListPIntermediosDisp.getSelectedValue());
        
        String j=jListPIntermediosDisp.getSelectedValue();
        String[] i=j.split("-");
        ArrayList<Integer> l=new ArrayList();
        
        for(Integer h : tourTemp.getPIntermedios()){
           if(h!=Integer.parseInt(i[0])){
               l.add(h);
           }
        }
        tourTemp.setPIntermedios(l);
        jListPIntermediosSelect.setModel(modelo);
        agregar.removeElement(jListPIntermediosDisp.getSelectedValue());
        jListPIntermediosDisp.setModel(agregar);
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        String j=jListPIntermediosSelect.getSelectedValue();
        agregar.addElement(jListPIntermediosSelect.getSelectedValue());
        modelo.removeElement(jListPIntermediosSelect.getSelectedValue());
        String[] i=j.split("-");
        tourTemp.getPIntermedios().add(Integer.parseInt(i[0]));
        jListPIntermediosSelect.setModel(modelo);
        jListPIntermediosDisp.setModel(agregar);
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
       if(pin==1) tourTemp.setPInicial(0);
       if(pfin==1) tourTemp.setPFinal(0);
       tour.setPInicial(tourTemp.getPInicial());
       tour.setPFinal(tourTemp.getPFinal());
       tour.setPIntermedios(tourTemp.getPIntermedios());
       imodif.actualizar();
       this.dispose();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jCheckBoxPFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPFinalActionPerformed
       if(pfin==1){
           pfin=0;
       }else{
           pfin=1;
       }
    }//GEN-LAST:event_jCheckBoxPFinalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxPFinal;
    private javax.swing.JCheckBox jCheckBoxPInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelPFinal;
    private javax.swing.JLabel jLabelPInicial;
    private javax.swing.JList<String> jListPIntermediosDisp;
    private javax.swing.JList<String> jListPIntermediosSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
