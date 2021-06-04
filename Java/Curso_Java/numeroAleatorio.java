import java.util.Scanner;

public class numeroAleatorio
 {
    static double numero;

      public static void main(String args[])
      	{
		int i=0;
		Scanner j=new Scanner(System.in);

          while (i!=0){
          numero = Math.random(); 
          System.out.println("numero aleatorio entre 0 y 1 "+numero);
		  i=j.nextInt();
          }
 System.out.println("");
 
  	
 }
}