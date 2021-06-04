import java.util.*;

public class Tokens{
   public static void main (String[] args) {

      Scanner sc = new Scanner (System.in);
      System.out.print("Ingrese la oracion: ");
      String s=sc.nextLine();
      StringTokenizer st=new StringTokenizer(s," ");//" " el espacio en blanco
      while (st.hasMoreTokens() ) {
        String s1 = st.nextToken();
        System.out.println(s1);
      }

      System.out.println("Termino el ciclo");
   }
}