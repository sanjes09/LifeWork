/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Obra;

import Controlador.Admin_O;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author LENOVO
 */
public class IObras extends javax.swing.JFrame {
    private boolean flag; //True = Consultar
                          //False = Modificar
    
    private int contClicks = 0;
    
    public IObras(boolean a) {
        initComponents();
        flag = a;
    }
    
      @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JButtonvolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxInicial = new javax.swing.JComboBox<>();
        jButtonBuscar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        JTablelistaIni = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED - Consulta de Obras");
        setResizable(false);

        JButtonvolver.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JButtonvolver.setText("Continuar");
        JButtonvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonvolverActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Inicial del apellido:");

        jComboBoxInicial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"}));

        jButtonBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        JTablelistaIni.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        JTablelistaIni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTablelistaIni.setShowHorizontalLines(false);
        JTablelistaIni.setShowVerticalLines(false);
        JTablelistaIni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JTablelistaIniMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(JTablelistaIni);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Autores con sus obras:");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Seleccione una obra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JButtonvolver))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jComboBoxInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonBuscar))
                        .addComponent(jLabel2)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JButtonvolver)
                    .addComponent(jLabel3))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTablelistaIniMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablelistaIniMouseReleased
        Admin_O ctrlObras = Admin_O.getInstance();
        //MANEJADOR DE EVENTOS DEL MOUSE
        contClicks++;
        if(contClicks > 2) contClicks = 0;
        if(contClicks == 2) {
            // OBTENCION DE ID DE LA OBRA SELECCIONADA
            int fila = JTablelistaIni.getSelectedRow();
            String data = JTablelistaIni.getValueAt(fila,0).toString();
            //FLAG PARA SABER SI ES CONSULTA O MODIFICACIÓN
            //CONSULTA
            if(flag){
                IUnaO iConsultarObra = new IUnaO(data);
                iConsultarObra.setVisible(true);
                iConsultarObra.setLocationRelativeTo(null);
                this.dispose();
                //MODIFICACION
            }else{
                //ctrlObras.modificar(data);
                IModO iModObra = new IModO(data);
                iModObra.setVisible(true);
                iModObra.setLocationRelativeTo(null);
                this.dispose();
            }
        }
    }//GEN-LAST:event_JTablelistaIniMouseReleased

    private void JButtonvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonvolverActionPerformed
        Admin_O ctrlObras = Admin_O.getInstance();
        ctrlObras.instanceAdmObras();
        this.dispose();
    }//GEN-LAST:event_JButtonvolverActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        Admin_O ctrlObras = Admin_O.getInstance();
        ArrayList<String[]> aux = ctrlObras.buscarObra_Autor(jComboBoxInicial.getSelectedItem().toString());
        DefaultTableModel modelo = (DefaultTableModel)JTablelistaIni.getModel();
        Object dataFila[] = new Object[3];
        //BORRAR ELEMENTOS PREVIOS DE LA TABLA
        int contFila = modelo.getRowCount();
        for (int i=contFila-1;i>=0;i--){
            modelo.removeRow(i);
        }
        //GUARDAR ELEMENTOS FILTRADOS DE LA BÚSQUEDA EN LA TABLA
        for(int i=0;i<aux.size();i++){
            String[] aux2  = aux.get(i);
            dataFila[0] = aux2[0];
            dataFila[1] = aux2[1];
            dataFila[2] = aux2[2];
            modelo.addRow(dataFila);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonvolver;
    private javax.swing.JTable JTablelistaIni;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JComboBox<String> jComboBoxInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
