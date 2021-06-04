/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.PI;

import Controlador.Admin_PI;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class IModDisp extends javax.swing.JFrame {
    String ubicacion;
    char disponibilidad;
    int coordenada;
    ArrayList<String> secuenciaID;
    int coordenadaAnterior;
    
    /**
     * Creates new form I_DispoPI
     */
    public IModDisp() {
        initComponents();
    }
    
    public IModDisp(String ubicacion, char disponibilidad, int coordenada, ArrayList<String> secuenciaID, int coordenadaAnterior) {
        initComponents();
        
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
        this.coordenada = coordenada;
        this.secuenciaID = secuenciaID;
        this.coordenadaAnterior = coordenadaAnterior;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        DisponibilidadS = new javax.swing.JRadioButton();
        DisponibilidadE = new javax.swing.JRadioButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED - Modificar disponibilidad");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Seleccione Disponibilidad:");

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jButton1.setText("Continuar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(DisponibilidadS);
        DisponibilidadS.setText("S");
        DisponibilidadS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibilidadSActionPerformed(evt);
            }
        });

        buttonGroup1.add(DisponibilidadE);
        DisponibilidadE.setText("E");
        DisponibilidadE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibilidadEActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DisponibilidadS))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DisponibilidadE)
                            .addComponent(jButton1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisponibilidadS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DisponibilidadE)
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Admin_PI ctrlPI = Admin_PI.getInstance(); 
        
       //disponibilidad = 'S';
       //System.out.println("Ubicacion: " + ubicacion + ", disponibilidad: " +  disponibilidad + ", coordenada: " + coordenada+ ", secuenciaID: " +  secuenciaID+ ", coordenadaAnterior: " + coordenadaAnterior);
        ctrlPI.modificarPI(ubicacion, disponibilidad, coordenada, secuenciaID, coordenadaAnterior);
        
        ctrlPI.instanceAdmPI();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DisponibilidadSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibilidadSActionPerformed
        disponibilidad = 'S';
    }//GEN-LAST:event_DisponibilidadSActionPerformed

    private void DisponibilidadEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibilidadEActionPerformed
        disponibilidad = 'E';
    }//GEN-LAST:event_DisponibilidadEActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton DisponibilidadE;
    private javax.swing.JRadioButton DisponibilidadS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    // End of variables declaration//GEN-END:variables
}