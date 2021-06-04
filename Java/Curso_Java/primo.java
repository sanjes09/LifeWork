public class primo {
    public static void main(String[] args) {
    	int i=1, cont=0,k;
    	while (i<=10){
    		k=1; 
    	    cont=0;
    		while (k<=i){
    			if(i%k==0)
    				cont++;
    		    k++;
    		}
    		if (cont<=2)
    			System.out.println("el numero: "+i+" es primo");   		
    		i++;
    	}    	
    }
}
