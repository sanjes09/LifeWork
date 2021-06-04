import java.util.Scanner;

public class Ejercicio6{
    
    void numero(String s,String a,int b){
    	double l=0;
    	int i=0;
    	l=Double.parseDouble(s);
    	if (l!=0){
    		System.out.print("La cadena transformada en numero es: "+l);
    		l+=b;    		    		
    		System.out.println("Sumada: "+l);
    	}
    	else {
    		System.out.println("La cadena no se puede convertir a numero");
    		i=s.indexOf(a);
    		if (i!=0){
    			System.out.println("La posicion de "+a+"en la cadena es: "+i);
    		}
    		else{
    			System.out.println("No se encuentra "+a+"en la cadena");
    		}
    	}
    }
}
public class principal{
	public static void main (String[] args) {
		final String a="b";
		final int b=5;
		String s;
		Ejercicio6 k = new Ejercicio6;
		Scanner p = new Scanner (System.in);
		System.out.printn("Ingrese la cadena a evaluar");
		s=p.next();
		k.numero(s,a,b);
		
		
}
}