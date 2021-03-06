/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.PI;

import Controlador.Admin_PI;
import Data.Data;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class IPI extends javax.swing.JFrame {
    private int flag; //1 = Consultar
                      //2 = Modificar
                      //3 = Eliminar
    
    private int contClicks;
   
    public IPI(int a) {
        initComponents();
        flag = a;
        
        for(int i=0;i<12;i++){
            jComboBoxUbiPI.addItem(Data.ubicacion[i]);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBusqueda = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBoxUbiPI = new javax.swing.JComboBox<>();
        jButtonVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Artdmin-UCV - Buscar Punto de Interés");
        setResizable(false);

        jTableBusqueda.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTableBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Coordenada", "Disponibilidad", "Obras Asociadas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBusqueda.setShowHorizontalLines(false);
        jTableBusqueda.setShowVerticalLines(false);
        jTableBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableBusquedaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBusqueda);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxUbiPI.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBoxUbiPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUbiPIActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jButtonVolver.setText("Continuar");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jLabel1.setText("Seleccione ubicación:");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Punto de interés en ubicación seleccionada:");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Seleccione un punto de interés");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonVolver)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBoxUbiPI, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxUbiPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(jButtonVolver)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Admin_PI ctrlPI = Admin_PI.getInstance();
        DefaultTableModel modelo = (DefaultTableModel) jTableBusqueda.getModel();
        
        while(modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        
        String[] fila = new String[3];
        ArrayList<String[]> lista = ctrlPI.consultarPI(jComboBoxUbiPI.getSelectedItem().toString());        
        
        for(int i=0;i<lista.size();i++){
            String[] aux2  = lista.get(i);
            fila[0] = aux2[0];
            fila[1] = aux2[1];
            fila[2] = aux2[2];
            modelo.addRow(fila);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        Admin_PI ctrlPI = Admin_PI.getInstance();
        
        ctrlPI.instanceAdmPI();
        this.dispose();
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jTableBusquedaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBusquedaMouseReleased
        Admin_PI ctrlPI = Admin_PI.getInstance();
        
        contClicks++;
        
        if(contClicks > 2) contClicks = 0;
        
        if(contClicks == 2) {
            int fila = jTableBusqueda.getSelectedRow();
            
            int coordenada = (Integer.parseInt(jTableBusqueda.getValueAt(fila, 0).toString()));
            
            switch(flag) {
                case 1:
                {
                    IConPI iConsultarPI = new IConPI(coordenada);
                    
                    iConsultarPI.setVisible(true);
                    iConsultarPI.setLocationRelativeTo(null);
                    this.dispose();
                    break;
                }
                case 2:
                {
                    IModPI iModificarPI = new IModPI(coordenada);

                    iModificarPI.setVisible(true);
                    iModificarPI.setLocationRelativeTo(null);
                    this.dispose();
                    break;
                }
                case 3:
                {
                    IEliPI iEliminarPI = new IEliPI(coordenada);
                    
                    iEliminarPI.setVisible(true);
                    iEliminarPI.setLocationRelativeTo(null);
                    this.dispose();
                    break;
                }
            }
        }
    }//GEN-LAST:event_jTableBusquedaMouseReleased

    private void jComboBoxUbiPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUbiPIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUbiPIActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxUbiPI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBusqueda;
    // End of variables declaration//GEN-END:variables
}
