package Modelo.Obra;

import Modelo.PI.PuntoInteres;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Icon;

public class Patrimonio {
    //ATRIBUTOS
    private final ArrayList<Obra> conjunto;
    //MÉTODOS
        //CONSTRUCTOR
        public Patrimonio(){
            conjunto = new ArrayList(); 
        }
    
        //FUNCIÓN QUE VERIFICA SI UNA CADENA DE STRING ES SÓLO NÚMERICA
        private boolean esNumero(String prueba){
           
            char[] aux= prueba.toCharArray();
            for(int i=0;i<prueba.length();i++){
                if(aux[i]<48 || aux[i]>57){
                    return false;
                }
            }
            return true;
        
        }
    
        //FUNCIÓN QUE VERIFICA SI UNA CADENA DE STRING CONTIENE SÓLO LETRAS
        private boolean esPalabra (String prueba){
           
            char[] aux= prueba.toCharArray();
            for(int i=0;i<prueba.length();i++){
                if( (aux[i]<65 || aux[i]>90) && (aux[i] < 97 || aux[i] > 122) && (aux[i] < 128 || aux[i] > 165) && (aux[i] < 181 || aux[i] > 183) && (aux[i] < 210 || aux[i] > 212) && (aux[i] < 224 || aux[i] > 237) && (aux[i]!= 241) && (aux[i]!= 209)){
                    int ascii = (int) aux[i];
                    System.out.println(ascii);
                    return false;
                }
            }
            return true;
       
        }
    
        //REGISTRA UNA OBRA EN EL PATRIMONIO (conjunto)
        public String registrar(String titulo, String identificador, String aCreacion, String nombreA,
        String ubicacion, char estatus, String descripcion, Icon imagen, String apellidoA){
           
            String mensaje = getValidar(identificador, aCreacion, nombreA,apellidoA,"");
            if(mensaje.equals("")){
                //TITULO
                if(titulo.equals("")){
                   titulo = "Sin título";
                }
                //CAMPO DEL APELLIDO VACIO
                if(apellidoA.equals("")){
                    apellidoA = "Anónimo";
                }
                //REGISTRO DE LA OBRA
                conjunto.add(crearObra(titulo,identificador,aCreacion,nombreA,ubicacion,estatus,descripcion,imagen,apellidoA));
                System.out.println("CARD(conjunto)=" + conjunto.size());
            }    
            return mensaje;
       
        }
        
        private Obra crearObra(String titulo, String identificador, String aCreacion, String nombreA, String Ubicacion,
        char estatus, String descripcion,Icon imagen, String apellidoA){
            
            Obra obra = null;
        
            if(identificador.startsWith(Obra.TYPE_ESCULTURA)){
             obra=new Escultura(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
            }
            if(identificador.startsWith(Obra.TYPE_ARQUITECTURA)){
                obra=new Arquitectura(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
            }
            if(identificador.startsWith(Obra.TYPE_VITRAL)){
                obra=new Vitral(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
            }
            if(identificador.startsWith(Obra.TYPE_ELEMENTO_PLASTICO)){
                obra=new ElementoPlastico(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
            }
            if(identificador.startsWith(Obra.TYPE_MOSAICO)){
                obra=new Mosaico(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
            }
            if(identificador.startsWith(Obra.TYPE_FOTO)){
                obra=new Fotografia(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA);
            }
            //Mural
            if(identificador.startsWith(Obra.TYPE_MURAL)){
                obra = new Mural(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, null);
            }
            if(identificador.startsWith(Obra.TYPE_MURAL_DIPTICO)){
                Panel p = new Diptico();
                obra = new Mural(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, p);
            }
            if(identificador.startsWith(Obra.TYPE_MURAL_TRIPTICO)){
                Panel p = new Triptico();
                obra = new Mural(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, p);
            }
            if(identificador.startsWith(Obra.TYPE_MURAL_POLIPTICO)){
                Panel p=null;
                try{
                    p = new Poliptico(4);
                }catch(Exception e) {
                    System.out.println(e.getMessage());
                }    
                obra = new Mural(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, p);
            }
            
            //pintura
            if(identificador.startsWith(Obra.TYPE_PINTURA)){
                obra = new Pintura(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, null);
            }
            if(identificador.startsWith(Obra.TYPE_PINTURA_DIPTICO)){
                Panel p = new Diptico();
                obra = new Pintura(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, p);
            }
            if(identificador.startsWith(Obra.TYPE_PINTURA_TRIPTICO)){
                Panel p = new Triptico();
                obra = new Pintura(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, p);
            }
            if(identificador.startsWith(Obra.TYPE_PINTURA_POLIPTICO)){
                Panel p=null;
                try{
                    p = new Poliptico(4);
                }catch(Exception e) {
                    System.out.println(e.getMessage());
                }    
                obra = new Pintura(titulo, identificador, aCreacion, nombreA, Ubicacion, estatus, descripcion, imagen, apellidoA, p);
            }      
            return obra;
        }
        
        private String getValidar(String ID_Obra, String aCreacion, String nombreA,String apellidoA, String identificadorAnterior){
            String mensaje=""; 
            // VALIDACIONES DE ID_OBRA
            String[] auxID;
            auxID = ID_Obra.split("-");
            // FORMATO
            //TAMAÑO
            if(auxID.length!=3){
                mensaje = "ID inválido";
            }
            //SUBSTRING NUM 1 Y NUM 2 VÁLIDO
            for(int i=0;i<2;i++){
                if (esNumero(auxID[i])){
                     mensaje = "ID inválido";
                }
            }
            //SUBSTRING NUM 3 VÁLIDO
            if(!esNumero(auxID[2])){
                mensaje = "ID inválido";
            }   
            
            if(!ID_Obra.equals(identificadorAnterior)){
                //ID REPETIDO
                for (Obra auxObra : conjunto) {
                    if(ID_Obra.equals(auxObra.get_Identificador())){
                        mensaje = "El ID ya existe";
                    }
                }   
            }
                    
            //VALIDACIONES DE LAS CARACTERISTICAS
            //AÑO DE CREACIÓN
            String[] auxObra;
            auxObra=aCreacion.split("-");
            //LONGITUD DE LAS FECHAS Y VERIFICAR QUE SEAN NÚMEROS
            for (String año : auxObra) {
                if (año.length() != 4 || !esNumero(año)){
                    mensaje = "Año de creación inválido";
                }
            }
            // ¿AÑO_A < AÑO_B?
            if(auxObra.length == 2){
                int[] auxAño= new int[2];
                auxAño[0] = Integer.parseInt(auxObra[0]);
                auxAño[1] = Integer.parseInt(auxObra[1]);
                if (auxAño[0] > auxAño[1] || auxAño[0] == auxAño[1]){
                    mensaje = "Año de creación inválido";
                }
            }
            //NOMBRE DEL AUTOR
            //¿ES UN NOMBRE VÁLIDO?
            if(!esPalabra(nombreA)){
                 mensaje = "Nombre del autor inválido";
            }
                    
            //APELLIDO DEL AUTOR
            //¿ES UN NOMBRE VÁLIDO?
            if(!esPalabra(apellidoA)){
                    mensaje = "Apellido del autor inválido";
            }
            return mensaje;        
        }

        //MODIFICA UNA OBRA QUE ESTÉ EN PATRIMONIO (conjunto)
        public String modificar(String titulo, String ID_Obra, String aCreacion, String nombreA,
        String ubicacion, char estatus, String descripcion, Icon imagen, String apellidoA, String identificadorAnterior){
        
            String mensaje = getValidar(ID_Obra, aCreacion, nombreA,apellidoA,identificadorAnterior);
            if(mensaje.equals("")){
                //TITULO
                if(titulo.equals("")){
                   titulo = "Sin título";
                }
                //CAMPO DEL APELLIDO VACIO
                if(apellidoA.equals("")){
                    apellidoA = "Anónimo";
                }
                
                //Eliminar Obra Anterior
                for(int i = 0; i < conjunto.size(); i++){
                    if(conjunto.get(i).get_Identificador() == identificadorAnterior) {
                        conjunto.remove(conjunto.get(i));
                        break;
                    }
                }
                //MODIFICAR
                conjunto.add(crearObra(titulo,ID_Obra,aCreacion,nombreA,ubicacion,estatus,descripcion,imagen,apellidoA));
                System.out.println("CARD(conjunto)=" + conjunto.size());
            }  
            return mensaje;
        }
       
        //CONSULTA UNA OBRA QUE ESTÉ EN PATRIMONIO (conjunto)
        public Obra consultar(String ID_Obra){
            
            for(Obra obraAux : conjunto){
                if(ID_Obra.equals(obraAux.get_Identificador())){
                    return obraAux;
                }
            }
            return null;
        
        }
        
        //BUSCA UNA OBRA EN PATRIMONIO CUYO AUTOR TENGA LA INICIAL DEL APELLIDO DADO
        public ArrayList<String[]> buscarObra_Autor(String apellidoA){
            
            ArrayList<String[]> subconjunto_Obras = new ArrayList();
            for (Obra auxObra : conjunto){
                if (auxObra.get_ApellidoA().charAt(0) == apellidoA.charAt(0)){
                    String dataFila[] = new String[3];
                    dataFila[0] = auxObra.get_Identificador();
                    dataFila[1] = auxObra.get_Titulo();
                    dataFila[2] = auxObra.get_ApellidoA() + " " + auxObra.get_NombreA();
                    subconjunto_Obras.add(dataFila);
                }
            }
            System.out.println("CARD(subconjunto_Obras)=" + subconjunto_Obras.size());
            return subconjunto_Obras;
        
        }

        public ArrayList<HashMap> buscarObrasUbi(String ubicacion) {
        
            ArrayList<HashMap> lista = new ArrayList();
            for(Obra obra: conjunto){
                if(obra.get_Ubicacion().equals(ubicacion)) {
                    HashMap<String, String> obraH = new HashMap();
                    obraH.put("Identificador", obra.get_Identificador());
                    obraH.put("Titulo", obra.get_Titulo());
                    obraH.put("Autor", obra.get_ApellidoA() + ' ' + obra.get_NombreA()); 
                    lista.add(obraH);
                }
            }        
            return lista;
        }
        
        //
        public ArrayList<Obra> buscarSecuenciaID(ArrayList<String> secuenciaID) {
        
        ArrayList<Obra> lista = new ArrayList();
        
        for(String id: secuenciaID){
            for(Obra obra: conjunto){
                if(obra.get_Identificador().equals(id)){
                    lista.add(obra);
                }
            }
        }
        return lista;
        }
        
        //
        public ArrayList<Obra> getConjuntoObras(){
         return conjunto;
        }
        
        
}
