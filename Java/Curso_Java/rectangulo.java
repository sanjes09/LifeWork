class rectangulo{
	//atributos de clas
	double base;
	double altura;
	
	rectangulo(double x, double y){
		base =x;
		altura=y;
	}
	
	rectangulo(double x){
		this.base=x;
	}
	
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
		rectangulo rec1=new rectangulo(5);	
	    rectangulo rec2=new rectangulo(3,4);
	    
		rec1.MostrarBase();
		rec2.MostrarAltura();
		rec2.MostrarBase();
		
		}
}