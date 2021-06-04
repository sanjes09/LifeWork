package Modelo.Obra;

import javax.swing.Icon;

public abstract class ObraDeArte extends Obra {
    
    public ObraDeArte(String titulo, String identificador, String aCreacion, String nombreA, String Ubicacion,
    char estatus, String descripcion, Icon imagen, String apellidoA){
        
        super(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
    
    }
    
}
