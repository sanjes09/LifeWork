class prueba3{
	public static void main (String[] args) {
		int n[] ={22,25};
		//int [] aux=new int [1]; 
		System.out.println("Valor de n en main antes de llamar al metodo: "+n[0]);
		incrementar(n);
		//n=aux[0];
		System.out.println("Valor de n en main despues de llamar al metodo: "+n[0]);
}
public static void  incrementar(int [] x){
	x[0]=18;
	System.out.println("Valor de n en el metodo despues de incrementar: "+x[0]);
}
}