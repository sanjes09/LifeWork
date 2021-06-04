public class Ejercicio2 {
    public static void main(String[] args) {
    	int par[] = new int [100];
	        int i=0;
	        int x=0;
	        int suma=0;
	        int cont=0;
	        int prom=0;
	
    	while (x<=100){
    		if (x%2==0){
    			par[i]=x;
    			//System.out.println("Los numeros pares son: "+par[i]);
    			suma+=x;
    			cont++;
    			i++;
    			}
    		x++;
    		    		
    		}
    		prom=suma/cont;
        System.out.println("el promedio del arreglo es: "+prom);
    	
    }
}
