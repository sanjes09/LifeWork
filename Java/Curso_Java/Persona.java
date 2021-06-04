public class Persona{
	
	protected String nombre;
	protected int edad;
	
/*	public Persona(String n, int e){
		nombre=n;
		edad=e;
	}
	*/
	void getDatos(){
		System.out.println("El nombre es: "+nombre);
		System.out.println("La edad es: "+edad);
	}
}

class Estudiante extends Persona {
	private double promedio;
	
	public Estudiante (String n, int e, double p){
		nombre=n;
		edad=e;
		promedio=p;
	}
	
	public double getPromedio(){
		return promedio;
	}
}
class personaEstudiante{
	public static void main (String[] args) {
		Estudiante e = new Estudiante ("Santiago",20,16.50);
		e.getDatos();
		System.out.println("El promedio es "+e.getPromedio());
		
}
}