package Vista.Tour;
import java.util.*;
import Controlador.Ctrl_AdminTour;
import Modelo.Tour.Tour;
import Modelo.PI.*;
import Data.Data;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class ICrearTour extends javax.swing.JFrame {
    Ctrl_AdminTour admin = Ctrl_AdminTour.instance();
    ConjuntoPI conjuntopi = Data.conjuntoPI;
    Tour tour=new Tour();
    ArrayList<PuntoInteres> cpi=conjuntopi.consultarTodo();
    int agg=0;
    public ICrearTour() {
        initComponents();
        tour.setDisp('N');
        jLabelNotificacion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIdentificador = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jButtonAgregarPI = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPIntermedios = new javax.swing.JList<>();
        jTextFieldPInicial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPFinal = new javax.swing.JTextField();
        jLabelNotificacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Crear Tour");

        jLabel1.setText("Identificador: ");

        jLabel2.setText("Nombre:");

        jButtonAgregarPI.setText("Agregar Punto de Interés");
        jButtonAgregarPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarPIActionPerformed(evt);
            }
        });

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

        jScrollPane1.setViewportView(jListPIntermedios);

        jLabel3.setText("Punto Inicial:");

        jLabel4.setText("Puntos Intermedios:");

        jLabel5.setText("Punto final:");

        jTextFieldPFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPFinalActionPerformed(evt);
            }
        });

        jLabelNotificacion.setText("Notificacion:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButtonAgregarPI)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jButtonConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(105, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNotificacion)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(jTextFieldPInicial)
                            .addComponent(jTextFieldPFinal))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonAgregarPI)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabelNotificacion)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonConfirmar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void disponibilidad(){
        char init='N',fin='N',inter='N';
        
        for(PuntoInteres pi : cpi){
            if(pi.get_Coordenada()==tour.getPInicial()&&pi.get_Disponibilidad()=='S'){
                init='S';
            }
            if(pi.get_Coordenada()==tour.getPFinal()&&pi.get_Disponibilidad()=='S'){
                fin='S';
            }
            if(tour.getPIntermedios()!=null){
                for(Integer coordenada : tour.getPIntermedios()){
                    if(pi.get_Disponibilidad()=='S'){
                       inter='S';
                    }
                } 
            }
            if(init=='S'&&fin=='S'&&inter=='S'){
                tour.setDisp('S');
            }else{
                tour.setDisp('N');
            }
        }
    }
    
    public void actualizar(){
        DefaultListModel<String> modelo = new DefaultListModel<>();
        int aux=tour.getPInicial();
        if(aux==0){
            jTextFieldPInicial.setText("...");
        }else{
            jTextFieldPInicial.setText(String.valueOf(tour.getPInicial())+"-"+conjuntopi.getPI(tour.getPInicial()).get_Ubicacion());
        }
        aux=tour.getPFinal();
        for(Integer t : tour.getPIntermedios()){
            modelo.addElement(String.valueOf(t)+"-"+conjuntopi.getPI(t).get_Ubicacion());
        }
        jListPIntermedios.setModel(modelo);
        if(aux==0){
            jTextFieldPInicial.setText("...");
        }else{
           jTextFieldPFinal.setText(String.valueOf(tour.getPFinal())+"-"+conjuntopi.getPI(tour.getPFinal()).get_Ubicacion());
        }
    }
    
    boolean existe(){
        for(Tour t : admin.getTours()){
            if(tour.getID()==t.getID()) return true;
        }
        return false;
    }
    
    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        tour.setID(Integer.parseInt(jTextFieldIdentificador.getText()));
        tour.setNombre(jTextFieldNombre.getText()); 
        disponibilidad();
        if(tour.getID()<0){
            jLabelNotificacion.setText("El ID del tour debe ser mayor a 0");
        }else{
            if(existe()){
                jLabelNotificacion.setText("El ID del tour ya existe");
            }
            else{
                admin.confirmar(tour);
                this.dispose();
                admin.admin_tour();
            }
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jTextFieldPFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPFinalActionPerformed
    }//GEN-LAST:event_jTextFieldPFinalActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
        admin.admin_tour();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonAgregarPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarPIActionPerformed
        if(agg==1){
            jLabelNotificacion.setText("Los puntos de interés ya fueron agregados");
        }
        if(agg==0){
            admin.agregarPI(tour, this, null);
            agg=1;
        }
    }//GEN-LAST:event_jButtonAgregarPIActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarPI;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelNotificacion;
    private javax.swing.JList<String> jListPIntermedios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldIdentificador;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPFinal;
    private javax.swing.JTextField jTextFieldPInicial;
    // End of variables declaration//GEN-END:variables
}
