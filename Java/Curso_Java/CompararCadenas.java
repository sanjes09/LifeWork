public class CompararCadenas {
	public static void main (String[] args) {
		String s1="buenos dias";
		String s2="Buenos Dias";
		//metodo equals
		System.out.print("s1 y s2 son iguales (equals)?: ");
		System.out.println(s1.equals(s2));
		//metodo equalsIgnoreCase
		System.out.print("s1 y s2 son iguales (equalsIgnoreCase)?:");
		System.out.println(s1.equalsIgnoreCase(s2));
		//metodo compareTo
		System.out.print("s1 y s2 son iguales (compareTo)?:");
		System.out.println(s1.compareTo(s2));
		//metodo compareToIgnoreCase
		System.out.print("s1 y s2 son iguales (compareToIgnoreCase)?:");
		System.out.println(s1.compareToIgnoreCase(s2));
		char b='b';
		char B='B';
		System.out.println("*****Nota*****");
		System.out.println("El valor de b es: "+(int)b);
		System.out.println("El valor de B es: "+(int)B);
	}    
}