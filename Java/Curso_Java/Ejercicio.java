import java.io.*;
import java.util.Scanner;

public class Ejercicio{
	static FileWriter archivo=null;
	static FileReader archivo2=null;
	static BufferedWriter bw;
	
	public static void main (String[] args) {
		BufferedReader br;
		Scanner s = new Scanner (System.in);
		String S="";
		String contenido="";
		String ruta = "D:/Mis Documentos/JavaSantiagoM/prueba.txt";		
		int i=-1;
		abrir(ruta);
		br=abrir2(ruta);
        
        while (i!=0){
        	System.out.println("1. Guardar frase en archivo");
        	System.out.println("2. leer de archivo");
        	System.out.println("0. Cerrar y salir");
        	i=s.nextInt();
        	System.out.println(i);
        	
        	s.nextLine();
        	switch (i){
        		case 1: System.out.println("Ingrese la frase a guardar");
        		         S=s.nextLine();
        		        
        		        if (bw!=null){
							escribir (S);
							vaciarBuffer();
						}
						break;
				
				case 2: contenido=leer(br);
		                while(contenido !=null){
			                  System.out.println(contenido);
			                  contenido=leer (br);
			                 }
			            break;
			    
			    case 3: cerrar();
			            break;
		
        		
		
	        }
        }
	}
	
	public static String leer(BufferedReader br){
		String contenido;
		try{
			contenido=br.readLine();
		} catch (IOException e){
			System.out.println("Error en la lectura del buffer");
			contenido=null;
		}
		return contenido;
	}
	
	public static BufferedReader abrir2 (String ruta){
		BufferedReader br;
		try{
			archivo2 =new FileReader(ruta);
			br= new BufferedReader (archivo2);
		} catch (FileNotFoundException r){
			br=null;
		}
		return br;
	}
	
	public static void abrir (String ruta){
		try{
			archivo=new FileWriter (ruta);
			bw=new BufferedWriter(archivo);
		} catch (IOException e){
			bw=null;
		}
	}
	
	public static void escribir (String S){
		try{
			bw.write(S);
			bw.newLine();
		} catch (IOException e){
			System.out.println("error de escritura en el archivo");
		}
	}
	
	public static void vaciarBuffer(){
		try{
			bw.flush();
		} catch (IOException e){
			System.out.println("Error vaciando el buffer");
		}
	}
	
	public static void cerrar(){
		try{
			archivo.close();
		} catch (IOException e){
			System.out.println("error cerrando el archivo");
		}
	}
}