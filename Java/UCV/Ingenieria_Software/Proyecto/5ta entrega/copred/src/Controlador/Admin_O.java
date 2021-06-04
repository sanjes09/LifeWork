package Controlador;

import Data.Data;
import Modelo.Obra.Obra;
import Vista.Obra.*;
import java.util.ArrayList;
import java.util.HashMap;  
import javax.swing.Icon;

public class Admin_O {
    //Atributos
    private static Admin_O uniqueCtrlObras = null;
    
    //CONSTRUCTOR
    private Admin_O(){}
    
    //MÉTODOS
        //Constructor SINGLETON
        public static Admin_O getInstance(){
            
            if(uniqueCtrlObras == null){ 
                uniqueCtrlObras = new Admin_O();
            } 
             return uniqueCtrlObras; 
        }
        
        //MÉTODO PARA IR A REGISTRAR OBRA EN PATRIMONIO, RETORNA MENSAJES A LA INTERFAZ
        public String registrar(String titulo, String ID_Obra, String añoCreacion, String nombreA,
        String ubicacion, char estatus, String descripcion, Icon imagen, String apellidoA){
            
            String mensaje;
            mensaje = Data.patrimonio.registrar(titulo, ID_Obra, añoCreacion, nombreA, ubicacion, estatus, descripcion, imagen, apellidoA);
            return mensaje;
        }
        
        //MÉTODO PARA IR A MODIFICAR OBRA EN PATRIMONIO, RETORNA MENSAJES A LA INTERFAZ
        public String modificar(String titulo, String ID_Obra, String añoCreacion, String nombreA,
        String ubicacion, char estatus, String descripcion, Icon imagen, String apellidoA, String identificadorAnterior){
            
            String mensaje = Data.patrimonio.modificar(titulo, ID_Obra, añoCreacion, nombreA, ubicacion, estatus, descripcion, imagen, apellidoA,identificadorAnterior);
            return mensaje;
        }
        
        public Icon get_Imagen(String ID_Obra){
            Obra o = Data.patrimonio.consultar(ID_Obra);
            return o.get_Imagen();
        }
       
        //MÉTODO PARA IR A CONSULTAR OBRA EN PATRIMONIO, RETORNA LA OBRA CONSULTADA
        public HashMap consultar(String ID_Obra){
            
            Obra o = Data.patrimonio.consultar(ID_Obra);
            HashMap<String, String> obra = new HashMap();
            obra.put("Titulo", o.get_Titulo());
            obra.put("Identificador", o.get_Identificador());
            obra.put("ACreacion", o.get_ACreacion());
            obra.put("ApellidoA", o.get_ApellidoA());
            obra.put("NombreA", o.get_NombreA());
            obra.put("Ubicacion", o.get_Ubicacion());
            obra.put("Estatus", o.get_Estatus()+"");
            obra.put("Descripcion", o.get_Descripcion());
            
            return obra;
        }
        
        /*MÉTODO PARA IR A BUSCAR OBRA POR INICIAL DE APELLIDO DEL AUTOR,
        RETORNA EL SUBCONJUNTO DE OBRAS QUE CUMPLIERON CON EL CRITERIO*/
        public ArrayList<String[]>  buscarObra_Autor(String apellidoA){
            
            return Data.patrimonio.buscarObra_Autor(apellidoA);

        }

    //INTERFACES
        //MÉTODO PARA CREAR LA INSTANCIA "I_AdmObras"
        public void instanceAdmObras(){
           
            IAdm_O admObras = new IAdm_O();
            admObras.setVisible(true);
            admObras.setLocationRelativeTo(null);
            
        }
        
        //MÉTODO PARA CREAR LA INSTANCIA "I_RegObra"
        public void instanceRegObra(){
       
        IRegO regObra = new IRegO();
        regObra.setVisible(true);
        regObra.setLocationRelativeTo(null);
    
    }
       
        //MÉTODO PARA CREAR LA INSTANCIA "I_buscarObra_Autor"
        public void instanceBuscarObra(boolean a){
        //True = Consultar ; False = Modificar
       
        IObras busObra = new IObras(a);
        busObra.setVisible(true);
        busObra.setLocationRelativeTo(null);
   
    } 

}
