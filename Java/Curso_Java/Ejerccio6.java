public class Ejerccio6{
    
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