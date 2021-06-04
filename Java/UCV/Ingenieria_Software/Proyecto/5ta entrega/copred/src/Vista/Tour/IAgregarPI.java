package Vista.Tour;
import java.util.*;
import Controlador.Ctrl_AdminTour;
import Modelo.Tour.Tour;
import Modelo.PI.*;
import Data.Data;
import javax.swing.*;

public class IAgregarPI extends javax.swing.JFrame {
    Ctrl_AdminTour admin = Ctrl_AdminTour.instance();
    ArrayList<PuntoInteres> cpi=Data.conjuntoPI.consultarTodo();
    DefaultListModel<String> agregar = new DefaultListModel<>();
    DefaultListModel<String> modelo = new DefaultListModel<>();
    Tour tour;
    ICrearTour crear=null;
    IModificar modificar=null;
    
    public IAgregarPI(Tour t, ICrearTour crearTour, IModificar crearModificar) {
        initComponents();
        crear=crearTour;
        modificar=crearModificar;
        tour=t;
        jComboBoxPInicial.addItem("...");
        jComboBoxPFinal.addItem("...");
        int p=0;
 
        for(PuntoInteres x : cpi){
            p=0;
            if(tour.getPIntermedios()!=null){
                for(Integer h : tour.getPIntermedios()){
                    if(h.equals(x.get_Coordenada())){
                        p=1;
                    }
                }
            }
            if((tour.getPInicial()!=x.get_Coordenada())&&(tour.getPFinal()!=x.get_Coordenada())&&(p==0)){
                jComboBoxPInicial.addItem(String.valueOf(x.get_Coordenada())+"-"+x.get_Ubicacion());
                jComboBoxPFinal.addItem(String.valueOf(x.get_Coordenada())+"-"+x.get_Ubicacion());
                modelo.addElement(String.valueOf(x.get_Coordenada())+"-"+x.get_Ubicacion());
            }
        }
        jListPIntermediosDisp.setModel(modelo);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem5 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem6 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem7 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem8 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem9 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem10 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem11 = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem12 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem13 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem14 = new javax.swing.JCheckBoxMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem16 = new javax.swing.JCheckBoxMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        list1 = new java.awt.List();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxPInicial = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButtonSeleccionar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxPFinal = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPIntermediosDisp = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListPIntermediosSelect = new javax.swing.JList<>();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("jCheckBoxMenuItem3");

        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText("jCheckBoxMenuItem4");

        jCheckBoxMenuItem5.setSelected(true);
        jCheckBoxMenuItem5.setText("jCheckBoxMenuItem5");

        jCheckBoxMenuItem6.setSelected(true);
        jCheckBoxMenuItem6.setText("jCheckBoxMenuItem6");

        jCheckBoxMenuItem7.setSelected(true);
        jCheckBoxMenuItem7.setText("jCheckBoxMenuItem7");

        jCheckBoxMenuItem8.setSelected(true);
        jCheckBoxMenuItem8.setText("jCheckBoxMenuItem8");

        jCheckBoxMenuItem9.setSelected(true);
        jCheckBoxMenuItem9.setText("jCheckBoxMenuItem9");

        jCheckBoxMenuItem10.setSelected(true);
        jCheckBoxMenuItem10.setText("jCheckBoxMenuItem10");

        jCheckBoxMenuItem11.setSelected(true);
        jCheckBoxMenuItem11.setText("jCheckBoxMenuItem11");

        jMenu1.setText("jMenu1");

        jCheckBoxMenuItem12.setSelected(true);
        jCheckBoxMenuItem12.setText("jCheckBoxMenuItem12");

        jCheckBoxMenuItem13.setSelected(true);
        jCheckBoxMenuItem13.setText("jCheckBoxMenuItem13");

        jCheckBoxMenuItem14.setSelected(true);
        jCheckBoxMenuItem14.setText("jCheckBoxMenuItem14");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jCheckBoxMenuItem16.setSelected(true);
        jCheckBoxMenuItem16.setText("jCheckBoxMenuItem16");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COPRED-Agregar Punto de Interés a un Tour");

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

        jLabel2.setText("Inicial:");

        jComboBoxPInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPInicialActionPerformed(evt);
            }
        });

        jLabel3.setText("Intermedio(s):");

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

        jLabel1.setText("Final:");

        jComboBoxPFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPFinalActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jListPIntermediosDisp);

        jScrollPane4.setViewportView(jListPIntermediosSelect);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jButtonConfirmar)
                        .addGap(40, 40, 40)
                        .addComponent(jButtonCancelar))
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxPFinal, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxPInicial, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonSeleccionar)
                                            .addComponent(jButtonRemover))))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jButtonSeleccionar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRemover)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
        agregar.addElement(jListPIntermediosDisp.getSelectedValue());
        modelo.removeElement(jListPIntermediosDisp.getSelectedValue());
        jListPIntermediosSelect.setModel(agregar);
        jListPIntermediosDisp.setModel(modelo);
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed

    private void jComboBoxPInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPInicialActionPerformed
    }//GEN-LAST:event_jComboBoxPInicialActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        modelo.addElement(jListPIntermediosSelect.getSelectedValue());
        agregar.removeElement(jListPIntermediosSelect.getSelectedValue());
        jListPIntermediosDisp.setModel(modelo);
        jListPIntermediosSelect.setModel(agregar);
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        String u="";
        String[] o;
        int y;
        ArrayList<Integer> x=new ArrayList();
        u=jComboBoxPInicial.getSelectedItem().toString();
        
        if((u.compareTo("...")==0)&&(modificar==null)){
            tour.setPInicial(0);
        }else if(u.compareTo("...")!=0){
            o=u.split("-");
            tour.setPInicial(Integer.parseInt(o[0]));
        }
        u=jComboBoxPFinal.getSelectedItem().toString();
        
        if((u.compareTo("...")==0)&&(modificar==null)){
            tour.setPFinal(0);
        }else if(u.compareTo("...")!=0){
            o=u.split("-");
            tour.setPFinal(Integer.parseInt(o[0]));
        }
        y=jListPIntermediosSelect.getModel().getSize();
        
        for(int i=0;i<y;i++){
            u=jListPIntermediosSelect.getModel().getElementAt(i);
            o=u.split("-");
            x.add(Integer.parseInt(o[0]));
        }
        if(tour.getPIntermedios()!=null){
            for(Integer id : tour.getPIntermedios()){
                x.add(id);
            }
        }
        tour.setPIntermedios(x);
        if(crear!=null) crear.actualizar();
        else modificar.actualizar();
        this.dispose();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jComboBoxPFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPFinalActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem10;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem11;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem12;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem13;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem14;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem16;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem5;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem8;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem9;
    private javax.swing.JComboBox<String> jComboBoxPFinal;
    private javax.swing.JComboBox<String> jComboBoxPInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList<String> jListPIntermediosDisp;
    private javax.swing.JList<String> jListPIntermediosSelect;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private java.awt.List list1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    // End of variables declaration//GEN-END:variables

}
