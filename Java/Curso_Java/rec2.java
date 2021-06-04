class rec2{
	//atributos de clas
	double base;
	double altura;
	
/*	Rectangulo(double x, double y){
		base =x;
		altura=y;
	}*/
	//metodo que calcula el area
	double area (){
		double resultado;
		resultado =base*altura;
		return (resultado);
	}
	//metodo que muestra el valor de la base
	void MostrarBase(){
		System.out.println("la base es: "+base);
	}
	//metodo que muestra el valor de la altura	
	void MostrarAltura(){
		System.out.println("la altura es: "+altura);
	}
}

class areaRectangulo{
	
	public static void main (String[] args) 
		{
		
	//	Rectangulo rec1=new Rectangulo(3,4);
	 rec2 rec1 = new rec2();
		rec1.MostrarAltura();
		rec1.MostrarBase();
		
		}
}