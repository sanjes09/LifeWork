package Recorrido;
import Modelo.Tour.Tour;
import Modelo.PI.*;
import Modelo.Obra.*;
import Data.Data;
import java.util.*;

public class Recorrido {
    private Tour tActual;
    private int puntoActual;
    private int obraActual;
    ConjuntoPI puntos=Data.conjuntoPI;
    String[] ObrasTotales;
    int indice=-1;
    int intermedio=0;
    int totalIntermedios;
    int termino=0;
    
    public int getPActual(){
        return puntoActual;
    }
    
    void getObras(){
        String k=""; int x=0;
        
        for(Object o : puntos.getPI(puntoActual).get_ObrasAsociadas()){
            if(x!=0) k=k.concat(",");
            else x=1;
            k=k.concat(o.toString());
        }
        ObrasTotales=k.split(",");
    }
    public Recorrido(Tour t){
        tActual=t;
        puntoActual=t.getPInicial();
        totalIntermedios=t.getPIntermedios().size();
        getObras();
    }   
  
    public boolean existeSiguiente(){
        if(indice+1>=ObrasTotales.length) return false;
        else return true;
    }
    public String siguienteObra(){
        indice++;
        if(ObrasTotales[indice]==null) return "no";
        else return ObrasTotales[indice];
    }
    public boolean existePi(){
        if(intermedio<totalIntermedios){
            termino=1;
        }
        if(termino==1){
            termino=2;
            return true;
        }
        return false;
    }
    public void siguientePi(){
        if(termino!=3){
            if(termino==0) {
                puntoActual=tActual.getPIntermedios().get(intermedio);
                termino=1;
            }
            else {
                if(intermedio<totalIntermedios){
                    puntoActual=tActual.getPIntermedios().get(intermedio);
                }
                else{
                    puntoActual=tActual.getPFinal();
                    termino=3;
                }
            }
            intermedio++;
            indice=-1;
            getObras();
        }
        else
            puntoActual=-1;
    }
}
