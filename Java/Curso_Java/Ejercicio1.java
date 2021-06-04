public class Ejercicio1 {

    public static void main(String[] args) {
    		int par[] = new int [100];
	        int i=0;
	        int x=0;
	
    	while (x<=100){
    		if (x%2==0){
    			par[i]=x;
    			System.out.println("Los numeros pares son: "+par[i]);
    			i++;}
    		x++;
    		    		
    		}
    }
}
