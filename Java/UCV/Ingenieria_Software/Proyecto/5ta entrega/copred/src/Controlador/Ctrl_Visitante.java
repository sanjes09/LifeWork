package Controlador;
import Vista.Admin.IAcceso;
import Vista.Recorrer.*;
import Modelo.Tour.Tour;
import java.util.*;
import Modelo.PI.*;
import Data.Data;
import Modelo.Obra.*;

public class Ctrl_Visitante {
    static Ctrl_Visitante unique_Instance = null;
    
    //Constructor SINGLETON
    static public Ctrl_Visitante instance(){
        if(unique_Instance==null){
            unique_Instance=new Ctrl_Visitante();
        }
        return unique_Instance;
    }
    
    public void salir(){
        IAcceso iAcceso = new IAcceso();
        iAcceso.setVisible(true);
        iAcceso.setLocationRelativeTo(null);
    }
    
    public void visitante(){
        IAcceso_Visitante visitante = new IAcceso_Visitante();
        visitante.setVisible(true);
        visitante.setLocationRelativeTo(null);
    }
    
    public void Consultar(){
        IConsultarTrDisp consultar = new IConsultarTrDisp();
        consultar.setVisible(true);
        consultar.setLocationRelativeTo(null);
    }
    
    public void recorrer(Tour t){
        IRecorrer recorrer=new IRecorrer(t);
        recorrer.setVisible(true);
        recorrer.setLocationRelativeTo(null);
    }
    
    public ArrayList<Tour> getToursDisponibles(){
        ArrayList<Tour> temporal= new ArrayList();
        ArrayList<Tour> disponible= new ArrayList();
        Ctrl_AdminTour admin= Ctrl_AdminTour.instance();
        temporal=admin.getTours();
        for(Tour t : temporal){
            if(t.getDips()=='S')
                disponible.add(t);
        }
        return disponible;
    }
    public ConjuntoPI getPI(){
        return Data.conjuntoPI;
    }
//    public Obra avanzarAObra(){
//        
//    }
    public void avanzarASigPi(){}
    public void caracteristicas(){}
}
