import java.util.Scanner;

class Teclado2{
	
	public static void main (String[] args) {
		int variableEntero;
		float variableFloat;
		String mensaje;
		
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("Ingrese la frease: ");
		while (entrada.hasNext()){
			mensaje=entrada.next();
			System.out.println("Mensaje ingresado: "+mensaje);
		}
		
		System.out.print("Ingrese un entero: ");
		variableEntero=entrada.nextInt();
		System.out.println("Entero ingresado: "+variableEntero);
		
		System.out.print("Ingrese un float: ");
		variableFloat=entrada.nextFloat();
		System.out.println("Float ingresado: "+variableFloat);
		
		
	}
}