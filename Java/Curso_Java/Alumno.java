public class Alumno{
	private String nombre;
	private String apellido;
	private Integer edad;
	
	public Alumno(String nombre, String apellido, Integer edad) throws Exception{
		this.nombre=nombre;
		this.apellido=apellido;
		if (edad <=0){
			throw new Exception("La debe edad ser mayor a cero");
		}else{
			this.edad=edad;
		}
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getApellido(){
		return apellido;
	}
	
	public Integer getEdad(){
		return edad;
	}
}

class prueba{
	public static void main (String[] args) {
		try{
			Alumno alu1 =new Alumno("Marcos","fernandez",2);
		} catch (Exception e){
			System.out.println("error");
		}
	    finally {
	    	System.out.println("Adios");
	    }
	}
}