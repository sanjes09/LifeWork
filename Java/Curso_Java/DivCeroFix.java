public class DivCeroFix{
	static void metodo(){
		int denom=0;
		int num=15;
		try{
			int R = num / denom;
			System.out.println("Estos no se imprime");
		} catch (ArithmeticException e){
			System.out.println("Division por cero");
		}
		System.out.println("Despues de la sentencia catch");
	}
	
	public static void main (String[] args) {
		metodo();
    }
}