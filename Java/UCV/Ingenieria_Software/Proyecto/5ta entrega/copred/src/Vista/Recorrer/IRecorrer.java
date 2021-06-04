package Vista.Recorrer;
import Controlador.Ctrl_Visitante;
import Data.Data;
import Modelo.Obra.*;
import java.awt.event.*; // Para el manejo de eventos, necesario para el Timer.
import javax.swing.Timer;
import Recorrido.Recorrido;
import Modelo.Tour.*;
import Modelo.PI.*;
import java.util.*;
import javax.swing.Icon;

public class IRecorrer extends javax.swing.JFrame {
    ArrayList<Obra> obras=Data.patrimonio.getConjuntoObras();
    ConjuntoPI pi=Data.conjuntoPI;
    Ctrl_Visitante admin= Ctrl_Visitante.instance();
    
    Recorrido paseo;
    String oID;
    int b=0, c=0;
    
    public IRecorrer(Tour t) {
        initComponents();
        paseo = new Recorrido(t);
        jLabelTour.setText(String.valueOf(t.getID())+"-"+t.getNombre());
        jTextPaneUbicacion.setText(Data.conjuntoPI.getPI(t.getPInicial()).get_Ubicacion());
        jLabelVisitar.setText("Puede visitar obras");
        b=1; c=0;
    }
    
    void limpiar(){
        jTextPaneID.setText(" ");
        jTextPaneTitulo.setText(" ");
        jTextPaneNombre.setText(" ");
        jTextPaneApellido.setText(" ");
        jTextPaneFecha.setText(" ");
        jTextPaneEstatus.setText(" ");
        jTextPaneDescripcion.setText(" ");
        jLabelImagen.setIcon(null);
    }
    
    //CREA EL TEMPORIZADOR
    ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
           jLabelTimer.setText("Tiempo agotado, debe avanzar a la siguiente obra");
           limpiar();
        }
    };
    Timer Reloj = new Timer(300000,action);
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        jButtonAvanzar = new javax.swing.JButton();
        jButtonVisitarPI = new javax.swing.JButton();
        jButtonAbandonar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneID = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneTitulo = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneNombre = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPaneApellido = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPaneFecha = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPaneUbicacion = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPaneDescripcion = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPaneEstatus = new javax.swing.JTextPane();
        jButtonVisitarO = new javax.swing.JButton();
        jLabelTour = new javax.swing.JLabel();
        jLabelVisitar = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelTimer = new javax.swing.JLabel();
        jLabelImagen = new javax.swing.JLabel();
        jLabelExito = new javax.swing.JLabel();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Recorrer");

        jButtonAvanzar.setText("Avanzar");
        jButtonAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAvanzarActionPerformed(evt);
            }
        });

        jButtonVisitarPI.setText("Visitar punto de interés");
        jButtonVisitarPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitarPIActionPerformed(evt);
            }
        });

        jButtonAbandonar.setText("Abandonar");
        jButtonAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbandonarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPaneID);

        jLabel1.setText("ID:");

        jLabel2.setText("Titulo:");

        jScrollPane2.setViewportView(jTextPaneTitulo);

        jLabel3.setText("Nombre:");

        jScrollPane3.setViewportView(jTextPaneNombre);

        jLabel4.setText("Apellido:");

        jScrollPane4.setViewportView(jTextPaneApellido);

        jLabel5.setText("Año de creación:");

        jScrollPane5.setViewportView(jTextPaneFecha);

        jLabel6.setText("Ubicación:");

        jScrollPane6.setViewportView(jTextPaneUbicacion);

        jLabel7.setText("Descripción:");

        jScrollPane7.setViewportView(jTextPaneDescripcion);

        jLabel8.setText("Estatus:");

        jScrollPane8.setViewportView(jTextPaneEstatus);

        jButtonVisitarO.setText("Visitar Obra");
        jButtonVisitarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitarOActionPerformed(evt);
            }
        });

        jLabelTour.setText("Tour Actual");

        jLabel11.setText("Imagen:");

        jLabelTimer.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabelTour)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelVisitar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonVisitarO))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jButtonAbandonar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonAvanzar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(jButtonVisitarPI))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTimer)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExito, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 75, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelTour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jLabelVisitar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonVisitarO)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelExito)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jButtonVisitarPI)
                                                    .addComponent(jButtonAvanzar))
                                                .addGap(26, 26, 26)
                                                .addComponent(jButtonAbandonar))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(28, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTimer)
                                .addGap(96, 96, 96))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(20, 20, 20)
                        .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jButtonVisitarPI.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVisitarPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitarPIActionPerformed
        paseo.siguientePi();
        limpiar();
        jLabelImagen.setText(" ");
        if(paseo.getPActual()!=-1){
            jLabelVisitar.setText("Puede visitar obras");
            b=1; c=1;
            jTextPaneUbicacion.setText(Data.conjuntoPI.getPI(paseo.getPActual()).get_Ubicacion());
        }
        else{
            jLabelVisitar.setText(" ");
            jLabelExito.setText("Tour recorrido exitosamente");
            b=0; c=0;
        }
    }//GEN-LAST:event_jButtonVisitarPIActionPerformed

    private void jButtonAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbandonarActionPerformed
        this.dispose();
        admin.Consultar();
    }//GEN-LAST:event_jButtonAbandonarActionPerformed

    private void siguiente(){
        String o;
        o=paseo.siguienteObra();
        for(Obra t : obras){
            if(t.get_Identificador().compareTo(o)==0){
                limpiar();
                jTextPaneID.setText(t.get_Identificador());
                jTextPaneTitulo.setText(t.get_Titulo());
                jTextPaneNombre.setText(t.get_NombreA());
                jTextPaneApellido.setText(t.get_ApellidoA());
                jTextPaneFecha.setText(t.get_ACreacion());
                jTextPaneEstatus.setText(String.valueOf(t.get_Estatus()));
                jTextPaneDescripcion.setText(t.get_Descripcion());
                jLabelImagen.setIcon(t.get_Imagen());
            }
        }
        Reloj.restart();
        Reloj.start();
    }
    
    private void jButtonAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAvanzarActionPerformed
        if(c==1){
            siguiente();
            if(paseo.existeSiguiente()){
                jLabelVisitar.setText("Puede avanzar siguiente obra");
            }else {
                jLabelVisitar.setText("Avance al siguiente punto de interés");
                c=0;
            }
            
        }
    }//GEN-LAST:event_jButtonAvanzarActionPerformed

    private void jButtonVisitarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitarOActionPerformed
        if(b==1){
            siguiente();
            if(paseo.existeSiguiente()){
                jLabelVisitar.setText("Puede avanzar siguiente obra");
                c=1;
            }else {
                jLabelVisitar.setText("Avance al siguiente punto de interés");
                c=0;
            }
            b=0;
        }
    }//GEN-LAST:event_jButtonVisitarOActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbandonar;
    private javax.swing.JButton jButtonAvanzar;
    private javax.swing.JButton jButtonVisitarO;
    private javax.swing.JButton jButtonVisitarPI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelExito;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelTimer;
    private javax.swing.JLabel jLabelTour;
    private javax.swing.JLabel jLabelVisitar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextPane jTextPaneApellido;
    private javax.swing.JTextPane jTextPaneDescripcion;
    private javax.swing.JTextPane jTextPaneEstatus;
    private javax.swing.JTextPane jTextPaneFecha;
    private javax.swing.JTextPane jTextPaneID;
    private javax.swing.JTextPane jTextPaneNombre;
    private javax.swing.JTextPane jTextPaneTitulo;
    private javax.swing.JTextPane jTextPaneUbicacion;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
