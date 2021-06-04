import java.io.*;
public class UsoBufferedReader{
	static FileReader archivo;
	public static void main (String[] args) {
		BufferedReader br;
		int caracter;
		String contenido="";
		String ruta= "D:/Mis Documentos/JavaSantiagoM/prueba.txt";
		br=abrir(ruta);
		contenido=leer(br);
		while(contenido !=null){
			System.out.println(contenido);
			contenido=leer (br);
		}
		cerrar();
	}
	
	public static BufferedReader abrir(String ruta){
		BufferedReader br;
		try{
			archivo=new FileReader(ruta);
			br= new BufferedReader (archivo);
		} catch (FileNotFoundException r){
			br=null;
		}
		return br;
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
	
	public static void cerrar(){
		try{
			archivo.close();
		} catch (IOException e){}
	}
}