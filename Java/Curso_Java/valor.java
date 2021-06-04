class PruebaPorValor{
	void cambio(int a){
		a=15;
		System.out.println("El valor de a en este metodo es: "+a);
	}
}
class PasePorValor{
	public static void main (String[] args) {
		PruebaPorValor P = new PruebaPorValor();
		int a=2;
		System.out.println("El valor de a antes de la llamada es: "+a);
		P.cambio(a);
		System.out.println("El valor de a despues de la llamada es: "+a);
}
}