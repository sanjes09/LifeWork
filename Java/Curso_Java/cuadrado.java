
	class cuadrado{
		double lado;
		cuadrado (){
			lado=10;
		}
		cuadrado (double lado){
		this.lado=lado;
	}
	double area(){
		return(lado*lado);
	}
	
	class AreaCuadrado{
		public static void main(String[] args) {
			cuadrado Cua_1=new cuadrado ();
			cuadrado Cua_2=new cuadrado (2);
			System.out.print("El area del objeto referenciado por Cua_1 es: ");
			System.out.println(Cua_1.area());
			System.out.print("El area del objeto referenciado por Cua_2 es: ");
			System.out.println(Cua_2.area());
}
    }
}

