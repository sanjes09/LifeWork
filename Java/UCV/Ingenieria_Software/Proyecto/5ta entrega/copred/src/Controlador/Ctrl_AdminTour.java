package Controlador;
import java.util.*;
import Vista.Tour.*;
import Modelo.Tour.Tour;
import Data.*;
import Vista.Admin.IAdm_Comite;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ctrl_AdminTour {
    static Ctrl_AdminTour uniqueInstance = null;
    static private ArrayList<Tour> conjuntoTour = new ArrayList();
    String tourFilePath = "src/Data/Tour.txt";
    FileWriter archivo=null;
    BufferedWriter bw;
    
    //Constructor SINGLETON
    static public Ctrl_AdminTour instance(){
        if (uniqueInstance==null){
            uniqueInstance=new Ctrl_AdminTour();
        }
        return uniqueInstance;
    }
    
    public void operacion(int op){
        if(op==1) crear();
        if(op==2) consultarTour();
        if(op==3) modificarTour();
        if(op==4) eliminarTour();
    }
    
    public void admin_tour(){
        IAdminTour tour = new IAdminTour();
        tour.setVisible(true);
        tour.setLocationRelativeTo(null);
    }
    
    public void crear(){
        ICrearTour crear = new ICrearTour();
        crear.setVisible(true);
        crear.setLocationRelativeTo(null);
    }
    
    public void consultarTour(){
        IConsultarTour consultar = new IConsultarTour();
        consultar.setVisible(true);
        consultar.setLocationRelativeTo(null);
    }
    
    public void eliminarTour(){
        IEliminarTour elim = new IEliminarTour();
        elim.setVisible(true);
        elim.setLocationRelativeTo(null);
    }
    
    public void modificarTour(){
        IModificar mod = new IModificar();
        mod.setVisible(true);
        mod.setLocationRelativeTo(null);
    }
    
    public void agregarPI(Tour t,ICrearTour x, IModificar y){
        IAgregarPI agregar = new IAgregarPI(t,x,y);
        agregar.setVisible(true);
        agregar.setLocationRelativeTo(null);
    }
    
    public void confirmar(Tour tour){
        int x=1;
        for(Tour t : conjuntoTour){
            if(t.getID()==tour.getID()){
                t.setDisp(tour.getDips());
                t.setID(tour.getID());
                t.setNombre(tour.getNombre());
                t.setPFinal(tour.getPFinal());
                t.setPInicial(tour.getPInicial());
                t.setPIntermedios(tour.getPIntermedios());
                x=0;
                break;
            }
        }
        if(x==1) conjuntoTour.add(tour);
    }
    
    public void cargarTours(ArrayList<Tour> Tours){
        
    }
    
    public void guardarTour(){
        String s="";
        try{
            archivo=new FileWriter(tourFilePath);
            bw=new BufferedWriter(archivo);
        } catch (IOException e){
            bw=null;
        }
        for(Tour tour : conjuntoTour){
            s="";
            s=s.concat(String.valueOf(tour.getID())+",,"+tour.getNombre()+",,"+String.valueOf(tour.getDips())+",,"+String.valueOf(tour.getPInicial())+",,");
            for(Integer h : tour.getPIntermedios()){
                s=s.concat(String.valueOf(h)+",");
            }
            s=s.concat(","+String.valueOf(tour.getPFinal()));
        }
        try{
            bw.write(s);
            bw.newLine();
        } catch (IOException e){
            System.out.println("error de escritura en el archivo");
        }
    }
    
    public void cancelar(){
        guardarTour();
        IAdm_Comite comite = new IAdm_Comite();
        comite.setVisible(true);
        comite.setLocationRelativeTo(null);
    }
    
    public void getPi(int num){}
    
    public ArrayList<Tour> getTours(){
        return conjuntoTour;
    }
    
    public void seleccionarTour(int id){}
    
    public void confirmarElim(Tour t){
        conjuntoTour.remove(t);
    }
    
    public void eliminarPi(Tour t, IModificar m){
        IEliminarPI elim = new IEliminarPI(t,m);
        elim.setVisible(true);
        elim.setLocationRelativeTo(null);
    }
    
    public void confirmarElimPi(int id){}
    
}
