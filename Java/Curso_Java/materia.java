package paquete1;

public class materia{
	int codigo;
	String nombre;
	
	public materia(int codigo, String nombre){
		this.codigo=codigo;
		this.nombre=nombre;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getNombre(){
		return nombre;
	}
}