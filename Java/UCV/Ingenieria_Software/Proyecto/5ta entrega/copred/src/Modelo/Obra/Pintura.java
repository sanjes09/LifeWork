package Modelo.Obra;

import javax.swing.Icon;

public class Pintura extends ObraDeArte {

    Panel panel = null;
    
    public Pintura(String titulo, String identificador, String aCreacion, String nombreA, String Ubicacion,
    char estatus, String descripcion,Icon imagen, String apellidoA, Panel p){
        
        super(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion,imagen, apellidoA);
        
        this.panel = p;
    }
    
    public Panel get_Panel(){
       return this.panel;
    }
    
    public void set_Panel(Panel p){
       this.panel = p;
    }
    
}
