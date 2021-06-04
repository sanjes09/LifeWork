import java.util.Scanner;

public class Prat1{
	
	static void comprobar(String s,char i, char a){
		char y;
		int x=0;
		int g=s.length();
		int conti=0;
		int conta=0;
		while (x<g){
			y=s.charAt(x);
			if (y==i){
				conti++;
			}
			if (y==a){
				conta++;
			}
			x++;
		}
		System.out.println("Hay "+conti+" apariciones de 'i' en la frase");
		System.out.println("Hay "+conta+" apariciones de 'a' en la frase");
	}
	
	
	public static void main (String[] args) {
		String s;
		char i='i';
		char a='a';
		Scanner leer =new Scanner (System.in);
		System.out.println("Introduzca la frase");
		s=leer.next();
		comprobar(s,i,a);
    }
	
}