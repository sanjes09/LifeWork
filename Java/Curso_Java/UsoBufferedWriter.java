import java.io.*;

public class UsoBufferedWriter{
	static FileWriter archivo=null;
	static BufferedWriter bw;
	
	public static void main (String[] args) {
		String S1 ="Este sera el contenido del nuevo archivo. ";
		String S2 ="Esto se copiara en la otra linea. ";
		String ruta = "C:/Users/Santiago/Desktop/JavaSantiagoM/prueba.txt";
		abrir(ruta);
		if (bw!=null){
			//escritura en el BufferedWriter
			escribir (S1);
			escribir (S2);
			vaciarBuffer();
			cerrar();
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
			bw.flush();   //obliga a vacier el buffer
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