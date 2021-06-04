package Modelo.PI;

import Data.Data;
import Modelo.Obra.Obra;
import java.util.ArrayList;
import java.util.HashMap;

public class ConjuntoPI {
    private ArrayList<PuntoInteres> conjuntoPI;
    
    //Constructor
    public ConjuntoPI() {
        conjuntoPI = new ArrayList();
    }
    
    public String validar(int coordenada){
       
        String mensaje ="";
        
        //Validar coordenada negativa
        if(coordenada < 0) {
            mensaje = "Coordenada inválida";
        }
        
        //Validar coordenada vacia
        if(coordenada == -1) {
            mensaje = "Ingrese coordenada";
        }
        
        //Validar coordenada única
        for (PuntoInteres auxPI : conjuntoPI) {
            if(coordenada == auxPI.get_Coordenada()){
                mensaje = "La coordenada ya existe";
            }
        }
        
        return mensaje;
    }
    
    public String cargar(String ubicacion, int coordenada, ArrayList<String> secuencia_ID, char disponibilidad){

        String mensaje = validar(coordenada);
        if(mensaje.equals("")){
            conjuntoPI.add(new PuntoInteres(ubicacion, disponibilidad, coordenada, secuencia_ID));
            System.out.println("CARD(conjuntoPI)=" + conjuntoPI.size());
        }
        
        return mensaje;
    }
    
    public String crear(String ubicacion, int coordenada, ArrayList<String> secuencia_ID){

        String mensaje = validar(coordenada);
        if(mensaje.equals("")){
            //Registro
            char dispo_default = 'N';
            conjuntoPI.add(new PuntoInteres(ubicacion, dispo_default, coordenada, secuencia_ID));
            System.out.println("CARD(conjuntoPI)=" + conjuntoPI.size());
        }
        
        return mensaje;
    }
    
    public void eliminar(int coordenada) {
        for(int i = 0; i < conjuntoPI.size(); i++){
            if(conjuntoPI.get(i).get_Coordenada() == coordenada) {
                conjuntoPI.get(i).set_Disponibilidad('E');
                conjuntoPI.remove(conjuntoPI.get(i));
                System.out.println("CARD(conjuntoPI)=" + conjuntoPI.size());
            }
        }
    }
    
    public ArrayList<String[]> consultar(String ubicacion) {
        ArrayList<String[]> lista = new ArrayList();
        
        for(PuntoInteres pi: conjuntoPI){
            if(pi.get_Ubicacion().equals(ubicacion)) {
                String dataFila[] = new String[3];
                dataFila[0] = pi.get_Coordenada() + ""; // ...
                dataFila[1] = pi.get_Disponibilidad() + "";
                dataFila[2] = pi.get_ObrasAsociadasString();
                lista.add(dataFila);
            }
        }
        System.out.println("CARD(lista)=" + lista.size());
        return lista;
    }
    
    public ArrayList<PuntoInteres> consultarTodo(){
        return conjuntoPI;
    }

    public String modificar(String ubicacion, char disponibilidad, int coordenada, ArrayList<String> secuencia_ID, int coordenadaAnterior){
        
        String mensaje =""; //
        if( coordenada != coordenadaAnterior){
           mensaje = validar(coordenada);        
        }
        if(mensaje.equals("")){
            //Eliminar PI Anterior
            for(int i = 0; i < conjuntoPI.size(); i++){
                if(conjuntoPI.get(i).get_Coordenada() == coordenadaAnterior) {
                    conjuntoPI.remove(conjuntoPI.get(i));
                    break;
                }
            }
            //Registrar PI Nuevo
            System.out.println("Ubicacion: " + ubicacion + ", disponibilidad: " +  disponibilidad + ", coordenada: " + coordenada+ ", secuenciaID: " +  secuencia_ID+ ", coordenadaAnterior: " + coordenadaAnterior);
            conjuntoPI.add(new PuntoInteres(ubicacion, disponibilidad, coordenada, secuencia_ID));
            System.out.println("CARD(conjuntoPI)=" + conjuntoPI.size());
        }    
        
        return mensaje;
    }
    
    public PuntoInteres getPI(int coordenada) {
        ArrayList a = new ArrayList();
        PuntoInteres pi = new PuntoInteres("",' ',-1, a);
        
        for(PuntoInteres aux : conjuntoPI) {
            if(aux.get_Coordenada() == coordenada){
                pi = aux;
            }
        }
        
        return pi;
    }
    
    public ArrayList<HashMap> getObrasAsociadaPI(int coordenada){
        ArrayList<HashMap> resultado = new ArrayList();
        
        ArrayList<Obra> lista = Data.patrimonio.buscarSecuenciaID(this.getPI(coordenada).get_ObrasAsociadas());
        for(Obra obra: lista){
            HashMap<String, String> obraH = new HashMap();
            obraH.put("Identificador", obra.get_Identificador());
            obraH.put("Titulo", obra.get_Titulo());
            obraH.put("Autor", obra.get_ApellidoA() + ' ' + obra.get_NombreA());
            System.out.println("coordenada=" + coordenada + ", Identificador=" + obra.get_Identificador());
            resultado.add(obraH);
        }
        return resultado;
    }
    
}
