import java.util.Scanner;

class principal{

    static int comprobar(String s){
		int i=0;
		char c;
		for (i=0 ; i<s.length(); i++){
			
			c=s.charAt(i);
			if (!(Character.isDigit(c))){
				System.out.println("La cadena no es un numero");
				return 0;
			}
		}
		return 1;
	}
    
    static void convertir(int x,String s,int b,String a){
    	double l=0;
    	int i;
    	
    	if (x==1){
    		l=Double.parseDouble(s);
    		if (l!=0){
    			System.out.print("La cadena transformada en numero es: "+l);
    			l+=b;    		    		
    			System.out.println("Sumada: "+l);
    		}
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
	public static void main (String[] args) {
		String a="b";
		int b=5; int x;
		String s;
		Scanner p = new Scanner (System.in);
		System.out.println("Ingrese la cadena a evaluar");
		s=p.next();
		x=comprobar(s);
		convertir(x,s,b,a);
		
			
		
		
		
			
}
}