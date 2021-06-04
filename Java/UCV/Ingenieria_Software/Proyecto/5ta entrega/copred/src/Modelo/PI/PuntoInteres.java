package Modelo.PI;

import java.util.ArrayList;

public class PuntoInteres {
    private String ubi;
    private char disp;
    private int coordenada;
    private ArrayList secObrasAsoc;

    public PuntoInteres(String ubi, char disp, int coordenada, ArrayList secObrasAsoc) {
        this.ubi = ubi;
        this.disp = disp;
        this.coordenada = coordenada;
        this.secObrasAsoc = secObrasAsoc;
    }

    public String get_Ubicacion() {
        return ubi;
    }

    public void set_Ubicacion(String ubi) {
        this.ubi = ubi;
    }

    public char get_Disponibilidad() {
        return disp;
    }

    public void set_Disponibilidad(char disp) {
        this.disp = disp;
    }

    public int get_Coordenada() {
        return coordenada;
    }

    public void set_Coordenada(int coordenada) {
        this.coordenada = coordenada;
    }

    public ArrayList get_ObrasAsociadas() {
        return secObrasAsoc;
    }

    public void set_ObrasAsociadas(ArrayList secObrasAsoc) {
        this.secObrasAsoc = secObrasAsoc;
    }
    
    public String get_ObrasAsociadasString() {
        String secuencia = "";
        
        if(!secObrasAsoc.isEmpty()){
            for(int i = 0; i < secObrasAsoc.size()-1; i++) {
                secuencia += secObrasAsoc.get(i) + ", "; 
            }

            secuencia += secObrasAsoc.get(secObrasAsoc.size()-1);
        }
        
        return secuencia;
    }
    
}
