public class ExtraccionCaracteres {
	public static void main (String[] args) {
		String S="Esta es la cadena de prueba";
		char c=S.charAt(3);
		System.out.println("charAt(3): "+c);
		
		char cadena[] = new char [22];
		cadena [0]='h';
		cadena [1]='o';
		cadena [2]='l';
		cadena [3]='a';
		cadena [4]=' ';
		S.getChars(0,17,cadena,5);
		System.out.println("getChars: ");
		System.out.println(cadena);
		int longitud = S.length();
		char cad[]=new char [longitud];
		cad=S.toCharArray();
		System.out.println("toCharArray: ");
		System.out.println(cad);
		String sub1=S.substring(21);
		String sub2=S.substring(8,27);
		System.out.println(sub1);
		System.out.println(sub2);
	} 
}