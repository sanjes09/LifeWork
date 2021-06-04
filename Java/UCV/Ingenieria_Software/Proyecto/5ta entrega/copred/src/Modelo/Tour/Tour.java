package Modelo.Tour;
import Modelo.PI.*;
import java.util.*;

public class Tour {
    private int identificador;
    private String nombre;
    private char disponibilidad;
    private int coordenadaInicial;
    private ArrayList<Integer> coordenadaIntermedios;
    private int coordenadaFinal;
    
    public Tour(){
        identificador=0;
        nombre="";
        disponibilidad=' ';
        coordenadaInicial=0;
        coordenadaIntermedios=null;
        coordenadaFinal=0;
    }
    
    public void setID(int x){
        identificador=x;
    }
    
    public void setNombre(String s){
        nombre=s;
    }
    
    public void setDisp(char c){
        disponibilidad=c;
    }
    
    public void setPInicial(int x){
        coordenadaInicial=x;
    }
    
    public void setPIntermedios(ArrayList<Integer> x){
        coordenadaIntermedios=x;
    }
    
    public void setPFinal(int x){
        coordenadaFinal=x;
    }
    
    public int getID(){
        return identificador;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public char getDips(){
        return disponibilidad;
    }
    
    public int getPInicial(){
        return coordenadaInicial;
    }
    
    public ArrayList<Integer> getPIntermedios(){
        return coordenadaIntermedios;
    }
    
    public int getPFinal(){
        return coordenadaFinal;
    }
}
