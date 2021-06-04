import java.util.*;

final class Operaciones {
	 private double x;
	 private double y;
	
	public Operaciones(double j, double k){
		x=j;
		y=k;
	}
	
	double suma(){
		double d;
		d=x+y;
		return d;
	}
	
	double resta(){
		double d;
		d=x-y;
	    return d;

	}
	
	double multi(){
		double d;
		d= x*y;
        return d;
	}
	
	double division(){
		double d;
		d=x/y;
        return d;
	}
}
class resultado{
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el primer valor: ");
		double x = sc.nextDouble();
		
		System.out.println("Ingrese el segundo valor: ");
		double y = sc.nextDouble();
		
		double z;
		Operaciones o = new Operaciones(x,y);
		
		System.out.println("x: "+x);
		System.out.println("y: "+y);
		
		System.out.println("1. Sumar");
		System.out.println("2. Restar");
		System.out.println("3. Multiplicar");
		System.out.println("4. Dividir");

		int i = sc.nextInt();
		
		switch (i){
			case 1: z=o.suma();
		            System.out.println("La suma de los numeros es: "+z);
		            break;
		    case 2: z=o.resta();
		            System.out.println("La resta de los numeros es: "+z);
		            break;
		    case 3: z=o.multi();
		            System.out.println("La multiplicacion de los numeros es: "+z);
		            break;
		    case 4: z=o.division();
		            System.out.println("La division de los numeros es: "+z);
		            break;
		}	
		
	}
}