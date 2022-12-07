package Vistas;

import Juego.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IMenu extends javax.swing.JFrame {
    
    int cond;
    
    public IMenu(int x) {
        cond=x;
        initComponents();
        if(cond==0) jLabel3.setText("Suerte!");
        if(cond==1) jLabel3.setText("GAME OVER!");
        if(cond==2) jLabel3.setText("GANASTE!");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cargarMapa = new javax.swing.JButton();
        pacmanImagen = new javax.swing.JLabel();
        rutaMapa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        vidas = new javax.swing.JTextField();
        empezarJuego = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.black);

        cargarMapa.setText("Cargar Mapa");
        cargarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarMapaActionPerformed(evt);
            }
        });

        pacmanImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/image.jpg"))); // NOI18N

        jLabel1.setText("Vidas:");

        vidas.setText("3");

        empezarJuego.setText("JUGAR!");
        empezarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empezarJuegoActionPerformed(evt);
            }
        });

        jLabel2.setText("Mapa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cargarMapa)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vidas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pacmanImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rutaMapa))
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(empezarJuego))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pacmanImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rutaMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(vidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cargarMapa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(empezarJuego)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(23, 23, 23))
        );

        cargarMapa.getAccessibleContext().setAccessibleName("cargarMapa");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void cargarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarMapaActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro=new FileNameExtensionFilter("TXT","txt");
        chooser.setFileFilter(filtro);
        
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();
            System.out.println(file);
            String fullPath = file.getAbsolutePath();
            
            rutaMapa.setText(fullPath);
        }
        
    }//GEN-LAST:event_cargarMapaActionPerformed

    
    private void empezarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empezarJuegoActionPerformed
        try {
            Controlador ctrl = new Controlador (rutaMapa.getText(),vidas.getText(),this);
            // Controlador ctrl = new Controlador (rutaMapa.getText(),vidas.getText(),this);
        } catch (IOException ex) {
            Logger.getLogger(IMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_empezarJuegoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargarMapa;
    private javax.swing.JButton empezarJuego;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel pacmanImagen;
    private javax.swing.JTextField rutaMapa;
    private javax.swing.JTextField vidas;
    // End of variables declaration//GEN-END:variables

}
