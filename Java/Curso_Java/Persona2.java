public class Persona2{
	
	protected String nombre;
	protected int edad;
	
	public Persona2(String n, int e){
		nombre=n;
		edad=e;
	}
	
	void getDatos(){
		System.out.println("El nombre es: "+nombre);
		System.out.println("La edad es: "+edad);
	}
	public void hablar(){
		System.out.println("Hola");
	}
}

class Estudiante extends Persona2 {
	private double promedio;
	
	public Estudiante (String n, int e, double p){
		super(n,e);
		//invocando al constructor de la clase padre (Persona)
		promedio=p;
	}
	
	public double getPromedio(){
		return promedio;
	}
	
	public void hablar(){
		System.out.println("Chao");
	}
}
class personaEstudiante{
	public static void main (String[] args) {
		double x;
		Estudiante e = new Estudiante ("Santiago",20,16.50);
		e.getDatos();
		System.out.println("El promedio es "+e.getPromedio());
		e.hablar();
		
}
}