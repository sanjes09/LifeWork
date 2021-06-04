class Persona3 
{

 /*atributos */
  
  private  String nombre;
  private  int edad;
  
    public Persona3(String n, int e)
    {
    	nombre=n;
    	edad=e;
    }
    
    public void getDatos()
    {
    	System.out.println("El nombre es : "+nombre);
    	System.out.println("La edad es : "+edad);
    }
    
    abstract void hablar ();
    	
}

class Estudiante extends Persona3
/* la clase estudiante hereda de persona (extends)*/
{
	private double promedio;
	
	 public Estudiante (String n, int e, double p)
	 {
	 	super(n,e);
	 //invocando al constructor de la padre(Persona)
	 	
	 	promedio=p;
	 }
	 
	 public double getPromedio()
	 {
	 	return promedio;
	 }
	 
	 public void hablar ()
    {
      System.out.println("Hola");	
    }	
}

class Profesor extends Persona3
{
	
	private int codigo;
	
	 public Profesor(String n, int e, int c)
	 {
	 	super(n,e);
	 	codigo=c;
	 }
	 
	 public void hablar()
	 {
	 	System.out.println("Soy Profe");
	 }
}
class personaEstudiante
{
	public static void main (String[] args)
		
    { Estudiante e = new Estudiante("Vanessa",28,16);
     e.getDatos();
     Profesor p = new Profesor("Judith",40,12);
     p.hablar();
    System.out.println
    	("El promedio es "+e.getPromedio());
    	e.hablar();
    }
}