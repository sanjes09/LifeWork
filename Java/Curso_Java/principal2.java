package paquete1.paquete2;
import paquete1.*;//estudiante;
//import paquete1.paquete2.preparador;

public class principal2{
	public static void main (String[] args) {
		preparador p = new preparador(1,"Vanessa",18400864);
		
		System.out.println("El codigo es: "+p.getCodigo());
		System.out.println("El nombre es: "+p.getNombre());
}
}