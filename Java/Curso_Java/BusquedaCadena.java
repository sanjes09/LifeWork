public class BusquedaCadena {
	public static void main (String[] args) {
		String S="Esta es la cadena original para la busqueda";
		String str="la";
		char caracter='a';
		//metodo indexOf
		System.out.print("La primera aparicion de: 'a' es en la pos: ");
		System.out.println(S.indexOf(caracter));
		System.out.print("La primera aparicion de: la, es en la pos: ");
		System.out.println(S.indexOf(str));
		//metodo lastIndexOf
		System.out.print("La ultima aparicion de: 'a' es en la pos: ");
		System.out.println(S.lastIndexOf(caracter));
		System.out.print("La ultima aparicion de: la, es en la pos: ");
		System.out.println(S.lastIndexOf(str));
	}
    
    
}