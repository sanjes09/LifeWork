package paquete1.paquete2;
import paquete1.estudiante;

public class preparador extends estudiante{
	public int codigo;
	
	public preparador(int codigo, String nombre, int cedula){
		super(cedula,nombre);
		this.codigo=codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String nombrePreparador(){
		return nombre;
	}
}