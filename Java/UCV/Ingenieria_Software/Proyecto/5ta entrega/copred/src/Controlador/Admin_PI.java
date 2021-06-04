package Controlador;

import Data.Data;
import Modelo.PI.PuntoInteres;
import Vista.PI.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin_PI {
    //Atributos
    private static Admin_PI uniqueCtrlPI = null;
    
    //Constructor
    private Admin_PI(){}
    
    //Metodos
    public static Admin_PI getInstance(){
         if(uniqueCtrlPI == null){ 
             uniqueCtrlPI = new Admin_PI();
         } 
         return uniqueCtrlPI;
    }
    
    public String crearPI(String ubicacion, int coordenada, ArrayList<String> secuencia_ID){
        return Data.conjuntoPI.crear(ubicacion, coordenada, secuencia_ID);
    }
    
    public void modificarPI(String ubicacion, char disponibilidad, int coordenada, ArrayList<String> secuencia_ID, int coordenadaAnterior){
        Data.conjuntoPI.modificar(ubicacion, disponibilidad, coordenada, secuencia_ID, coordenadaAnterior);
    }
    
    public ArrayList<String[]> consultarPI(String ubicacion){
        return Data.conjuntoPI.consultar(ubicacion);
    }
    
    public void eliminarPI(int coordenada){
        Data.conjuntoPI.eliminar(coordenada);
    }
    
    /*
    public ArrayList<Obra> buscarSecuenciaID(ArrayList<String> secuenciaID) {
        return Data.patrimonio.buscarSecuenciaID(secuenciaID);
    }*/
    
    public ArrayList<HashMap> buscarObrasUbi(String ubicacion) {
       return Data.patrimonio.buscarObrasUbi(ubicacion);
    }
    
    public PuntoInteres getPI (int coordenada) {
        return Data.conjuntoPI.getPI(coordenada);
    }
    
    public ArrayList<HashMap> getObrasAsociadasPI (int coordenada) {
        return Data.conjuntoPI.getObrasAsociadaPI(coordenada);
    }
    
    public String validarCoordenada(int coordenada, int coordenadaAnterior){
        String mensaje = "";
        if(coordenada != coordenadaAnterior){
            mensaje = Data.conjuntoPI.validar(coordenada);
        }    
        return mensaje;
    }
    
    //Interfaces
    public void instanceAdmPI(){
        IAdm_PI admPI = new IAdm_PI();
        
        admPI.setVisible(true);
        admPI.setLocationRelativeTo(null);
    }
    
    public void instanceCrearPI(){
        ICrearPI crearPI = new ICrearPI();
        
        crearPI.setVisible(true);
        crearPI.setLocationRelativeTo(null);
    }
     
    public void instanceModPI(){
        IModPI modPI = new IModPI();
        
        modPI.setVisible(true);
        modPI.setLocationRelativeTo(null);
    }
    
    public void instanceConsultarPI(){
        IConPI consPI = new IConPI();
        
        consPI.setVisible(true);
        consPI.setLocationRelativeTo(null);
    }
    
    public void instanceEliminarPI(){
        IEliPI elimPI = new IEliPI();
        
        elimPI.setVisible(true);
        elimPI.setLocationRelativeTo(null);
    }
    
    public void instanceBuscarPI(int a){
        //1 = Consultar
        //2 = Modificar
        //3 = Eliminar
        IPI buscarPI = new IPI(a);
        
        buscarPI.setVisible(true);
        buscarPI.setLocationRelativeTo(null);
    }
}
