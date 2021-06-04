class Objeto{
	int a;
	Objeto(int x){
		a=x;
	}
}
class PruebaPorReferencia{
	void cambio (Objeto obj){
		obj.a=15;
		System.out.println("El valor de a en el metodo es: "+obj.a);
	}
}
class PasePorReferencia{
	public static void main (String[] args) {
		Objeto obj = new Objeto (2);
		PruebaPorReferencia P = new PruebaPorReferencia();
		System.out.println("Antes de la llamada es: "+obj.a);
		P.cambio(obj);
		System.out.println("Despues de la llamada es: "+obj.a);
}
}