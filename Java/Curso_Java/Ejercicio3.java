import java.util.*;

public class Ejercicio3{
  private  String marca;
  private  String placa;
  
  public Ejercicio3 (String m, String p){
  	marca=m;
  	placa=p;
  }
  void getDatos(){
  	System.out.println("La marca del carro es: "+marca);
  	System.out.println("La placa es: "+placa);
  	}
}
class Particular extends Ejercicio3{
  	private int puertas;
  	
  	public Particular (String m, String p,int puer){
  		super(m,p);
  		puertas=puer;
  	}  
  		void getPuerta(){
  			System.out.println("Tiene: "+puertas+" puertas");
  		}	
  }
  
class Pasajero extends Ejercicio3{
  	private int cantidad;
  	
  	public Pasajero (String m, String p,int cant){
  				super(m,p);
  				cantidad=cant;
  	}  
  	
  	void getCantidad(){
  		System.out.println("Maximo: "+cantidad+" pasajeros");
  	}
  }

class Carro{
	public static void main (String[] args) {
		Scanner p = new Scanner (System.in);
		System.out.println("1.Crear vehiculo particular");
		System.out.println("2.Crear vehiculo de pasajeros");
		int v = p.nextInt();
		char l;
		if (v==1){
			System.out.println("Ingrese la placa");
			String c = p.next();
			System.out.println("Ingrese la cantidad de puertas");
			int pu=p.nextInt();
			System.out.println("Ingrese el nombre del vehiculo");
			String x= p.next();
			if ((c.startsWith("0"))||(c.startsWith("1"))||(c.startsWith("2"))||(c.startsWith("3"))||(c.startsWith("4"))||(c.startsWith("5"))||(c.startsWith("6"))||(c.startsWith("7"))||(c.startsWith("8"))||(c.startsWith("9"))){
  			System.out.println("Placa de vehiculo particular incorrecta... Debe empezar con una letra");}
  			else{
  				if ((pu <=5)&& (pu >=2)){
  				Particular K =new Particular(x,c,pu);
		        K.getDatos();
	        	K.getPuerta();}
	        	else{ 
	        		System.out.println("Cantidad de puertas incorrectas");
	        	}
  			}
		}
		if (v==2){
			System.out.println("Ingrese la placa");
			String y = p.next();
			System.out.println("Ingrese la cantidad de pasajeros");
			int pas = p.nextInt();
			System.out.println("Ingrese la marca del vehiculo");
			String z = p.next();
			if (((y.startsWith("0"))||(y.startsWith("1"))||(y.startsWith("2"))||(y.startsWith("3"))||(y.startsWith("4"))||(y.startsWith("5"))||(y.startsWith("6"))||(y.startsWith("7"))||(y.startsWith("8"))||(y.startsWith("9")))&& ((pas<=50)&&(pas>=20))){
					Pasajero P= new Pasajero(z,y,pas);
					P.getDatos();
					P.getCantidad();}
				else 
					System.out.println("Placa del vehiculo de pasajeros incorrecta... Debe empezar con un numero");
		}
		
		
		
}
}
