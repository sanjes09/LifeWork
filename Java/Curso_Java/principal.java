package paquete1;
import paquete1.estudiante;
import paquete1.materia;
//importa paquete1.*;

public class principal{
	public static void main (String[] args) {
		estudiante e = new estudiante(21194440,"Santiago");
		materia m = new materia(12,"Java");
		
		System.out.println(e.nombre);
		System.out.println("esta cursando "+m.getNombre());
		
	}
	
}