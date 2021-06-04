package Vista.Tour;
import Controlador.Ctrl_AdminTour;
import Data.Data;
import Modelo.PI.ConjuntoPI;
import Modelo.PI.PuntoInteres;
import Vista.Tour.IConsultarTour;
import Modelo.Tour.Tour;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class IModificar extends javax.swing.JFrame {

    Ctrl_AdminTour admin = Ctrl_AdminTour.instance();
    ArrayList<PuntoInteres> cpi=Data.conjuntoPI.consultarTodo();
    ConjuntoPI conjuntopi = Data.conjuntoPI;
    ArrayList<Tour> tours = admin.getTours();
    int select=0;
    Tour tourTemp=new Tour();
    Tour tourNew;
    
    public IModificar() {
        initComponents();
        
        int c=0;
        jLabelNotificacion.setText("");
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
    }
    
    public void actualizar(){
        DefaultListModel<String> modelo = new DefaultListModel<>();
        int aux=tourTemp.getPInicial();
        if(aux==0){
            jTextPanePInicial.setText("...");
        }else{
            jTextPanePInicial.setText(String.valueOf(tourTemp.getPInicial())+"-"+conjuntopi.getPI(tourTemp.getPInicial()).get_Ubicacion());
        }
        aux=tourTemp.getPFinal();
        for(Integer t : tourTemp.getPIntermedios()){
            modelo.addElement(String.valueOf(t)+"-"+conjuntopi.getPI(t).get_Ubicacion());
        }
        jListPIntermedios.setModel(modelo);
        if(aux==0){
            jTextPanePInicial.setText("...");
        }else{
           jTextPanePFinal.setText(String.valueOf(tourTemp.getPFinal())+"-"+conjuntopi.getPI(tourTemp.getPFinal()).get_Ubicacion());
        }
    }
    
    private void limpiar(){
        DefaultListModel<String> agregar = new DefaultListModel<>();
        jTextPaneDisponibilidad.setText("");
        jTextPaneIdentificador.setText("");
        jTextPaneNombre.setText("");
        jTextPanePInicial.setText("");
        jTextPanePFinal.setText("");
        jListPIntermedios.setModel(agregar);
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
        jButtonAgregarPI = new javax.swing.JButton();
        jButtonEliminarPI = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();
        jComboBoxTours = new javax.swing.JComboBox<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        jListPIntermedios = new javax.swing.JList<>();
        jLabelNotificacion = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane6.setViewportView(jTextArea1);

        jScrollPane8.setViewportView(jTextPane6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Modificar Tour");

        jLabel1.setText("Tours:");

        jLabel2.setText("Identificador:");

        jScrollPane2.setViewportView(jTextPaneIdentificador);

        jLabel3.setText("Nombre:");

        jScrollPane3.setViewportView(jTextPaneNombre);

        jLabel4.setText("Disponibilidad:");

        jScrollPane4.setViewportView(jTextPaneDisponibilidad);

        jLabel5.setText("Puntos de interés inicial:");

        jScrollPane5.setViewportView(jTextPanePInicial);

        jLabel6.setText("Puntos de interés Intermedios:");

        jLabel7.setText("Punto de interés final:");

        jTextPanePFinal.setToolTipText("");
        jScrollPane9.setViewportView(jTextPanePFinal);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonAgregarPI.setText("Agregar PI");
        jButtonAgregarPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarPIActionPerformed(evt);
            }
        });

        jButtonEliminarPI.setText("Eliminar PI");
        jButtonEliminarPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarPIActionPerformed(evt);
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

        jScrollPane10.setViewportView(jListPIntermedios);

        jLabelNotificacion.setForeground(new java.awt.Color(255, 0, 0));
        jLabelNotificacion.setText("Notificacion:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButtonAgregarPI)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarPI))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNotificacion)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonConfirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar))
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane9)
                            .addComponent(jComboBoxTours, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(36, 36, 36))
                                    .addComponent(jScrollPane4))))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelNotificacion)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregarPI)
                    .addComponent(jButtonEliminarPI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonConfirmar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarPIActionPerformed
        if(select==1) admin.agregarPI(tourTemp,null,this);
        else jLabelNotificacion.setText("No se ha seleccionado un tour");
    }//GEN-LAST:event_jButtonAgregarPIActionPerformed

    private void jButtonEliminarPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarPIActionPerformed
        if(select==1) admin.eliminarPi(tourTemp,this);
        else jLabelNotificacion.setText("No se ha seleccionado un tour");
    }//GEN-LAST:event_jButtonEliminarPIActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        tourTemp=null;
        this.dispose();
        admin.admin_tour();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jComboBoxToursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxToursActionPerformed
        limpiar();
        String y;
        String[] j;
        DefaultListModel<String> agregar = new DefaultListModel<>();
        y = jComboBoxTours.getSelectedItem().toString();
        if(y.compareTo("...")==0) select=0;
        else{
            jLabelNotificacion.setText("");
            select=1;
            j = y.split("-");
            y = j[0];
            String x;
            if(tours!=null){
                for(Tour t1 : tours){
                    x=String.valueOf(t1.getID());
                    if(x.compareTo(y)==0){ 
                        //tourNew=t1;
                        tourTemp.setDisp(t1.getDips());
                        tourTemp.setID(t1.getID());
                        tourTemp.setNombre(t1.getNombre());
                        tourTemp.setPFinal(t1.getPFinal());
                        tourTemp.setPInicial(t1.getPInicial());
                        tourTemp.setPIntermedios(t1.getPIntermedios());
                        
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
                                    System.out.println(coordenada);
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
        }
    }//GEN-LAST:event_jComboBoxToursActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        tourNew=tourTemp;
        admin.confirmar(tourNew);
        this.dispose();
        admin.admin_tour();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarPI;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonEliminarPI;
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
    private javax.swing.JScrollPane jScrollPane10;
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
