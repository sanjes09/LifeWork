package Data;

import Modelo.Obra.Patrimonio;
import Modelo.PI.ConjuntoPI;
import Modelo.Tour.Tour;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.io.*;


public class Data {
    String tourFilePath = "src/Data/Tour.txt";
    String obrasFilePath = "src/Data/Obras.txt";
    String piFilePath = "src/Data/PI.txt";
    String imgPath = "Imagenes/";
    
    //ATRIBUTOS
    public static Patrimonio patrimonio;
    public static ConjuntoPI conjuntoPI;
    public static String[] ubicacion;
    public static char[] estatus;
    //CONSTRUCTOR, SE USA PARA CARGAR LOS COMBOBOXES CON ESTA INFORMACIÓN PRE-SETEADA POR NOSOTROS
    public Data() {
        patrimonio = new Patrimonio();
        this.cargarObrasDelPatrimonio();
        conjuntoPI = new ConjuntoPI();
        this.cargarPI();
        ubicacion = new String[12];
        ubicacion[0] = "Facultad de Odontología";
        ubicacion[1] = "Facultad de Farmacia";
        ubicacion[2] = "Facultad de Ciencias";
        ubicacion[3] = "Plaza del Rectorado";
        ubicacion[4] = "Rectorado";
        ubicacion[5] = "Tierra de Nadie";
        ubicacion[6] = "Plaza entre Rectorado y Aula Magna";
        ubicacion[7] = "Aula Magna";
        ubicacion[8] = "entre Aula Magna y Biblioteca Central";
        ubicacion[9] = "Biblioteca Central";
        ubicacion[10] = "Sala de Conciertos";
        estatus = new char[3];
        estatus[0] = 'D';
        estatus[1] = 'M';
        estatus[2] = 'X';   
    }
    
    
    
    private void cargarObrasDelPatrimonio(){
        
        String content = "";   
        try{
            content = new Scanner(new File(obrasFilePath),"UTF-8").useDelimiter("\\Z").next();
            content = content.replaceAll("\n", "&&");
        }catch(FileNotFoundException e){
            System.out.println("ERROR: Archivo no encontrado"  + obrasFilePath);
        }catch(NoSuchElementException e){
            System.out.println("ERROR: Elemenot incorrecto al abrir:"  + obrasFilePath);
        }
        
        String[] lines = content.split("&&");
        
        for(String linea: lines){
            
            String[] datosObra = linea.split("##");
            String id = datosObra[0].trim();
            String titulo = datosObra[1].trim();
            String apellidoA = datosObra[2].trim();
            String nombreA = datosObra[3];
            if(!nombreA.equals("")){
                nombreA = nombreA.trim();
            }    
            String aCreacion = datosObra[4].trim();
            String ubicacion = datosObra[5].trim();
            String descripcion = datosObra[6];
            String imagenPath = imgPath + datosObra[7].trim();
            ImageIcon imagen = new ImageIcon(imagenPath);
            char estatus = datosObra[8].charAt(0);
            System.out.println("Por procesar obra: " + id);
            String mensaje = patrimonio.registrar(titulo, id, aCreacion, nombreA, ubicacion, estatus, descripcion, imagen, apellidoA);
            if(!mensaje.equals("")){
                System.out.println(mensaje);
            }    
        }
    }
    
    private void cargarPI(){
        
        String content = "";   
        try{
            content = new Scanner(new File(piFilePath),"UTF-8").useDelimiter("\\Z").next();
            content = content.replaceAll("\n", "&&");
        }catch(FileNotFoundException e){
            System.out.println("ERROR: Archivo no encontrado"  + piFilePath);
        }catch(NoSuchElementException e){
            System.out.println("ERROR: Elemenot incorrecto al abrir:"  + piFilePath);
        }
        
        String[] lines = content.split("&&");
        
        for(String linea: lines){
            
            String[] datosPI = linea.split("##");
            String coordenada = datosPI[0].trim();
            char disponibilidad = datosPI[1].charAt(0);
            String ubicacion = datosPI[3].trim();

            String[] idObras = datosPI[2].split(",");
            ArrayList<String> secuencia_ID = new ArrayList();
            for(String secuencia: idObras) {
                secuencia_ID.add(secuencia.trim()); 
                System.out.println("coordenada=" + coordenada + ", Identificador=" + secuencia.trim());
            }
            
            System.out.println("Por procesar pi : " + coordenada);
            String mensaje = conjuntoPI.cargar(ubicacion, Integer.parseInt(coordenada), secuencia_ID,disponibilidad);            
            if(!mensaje.equals("")){
                System.out.println(mensaje);
            }    
        }
    }
}
