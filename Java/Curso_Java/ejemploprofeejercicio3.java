import java.lang.String;
// char c; c=placa.charAt(0); c>='0' && c<='9'

class vehiculo
{
	
	String marca;
	String placa;
	
	
	public vehiculo (String marca,String placa)
	{
		this.marca=marca;
		this.placa=placa;
	}

	
	public String getMarca()
	{
	  return marca;
	}
	
	public String getPlaca()
	{
		return placa;
	}
} //fin class vehiculo


class particular extends vehiculo
 {
 	public int puertas;
 	
 	 public particular (String marca,String placa,int cant)
	   {
		 super(marca,placa);
		
     	   puertas=cant;
    	}
 	 
 	
 	 public int getPuertas()
 	 {
 	 	return puertas;
 	 }
 	
 }
 
 
class pasajeros extends vehiculo
 {
 	public int cantPasajeros;
 	
 	 public pasajeros (String marca,String placa,int cant)
	   {
		 super(marca,placa);
		 
		   cantPasajeros=cant;
     	   
       }
 	 
 	
 	 public int getCantPasajeros()
 	 {
 	 	return cantPasajeros;
 	 }
 	
 }
 
class ejercicio3_v3
 {
 	public static void main (String[] args) {
	
 	 	String placa="Asd";
 	 	char c;
 	 	
 /*	if (args.length > 1)
 		 { //si hay más de 1 parámetro
            System.out.println("Hay demasiados parámetros");
        } else if (args.length == 0) { //si no hay parámetros      
            System.out.println("Falta la placa");
        } else {
        	
          placa = args[0];
          
        }*/
        
        
        c=placa.charAt(0); //System.out.println("Primer Caracter: " +c);
        
        if ( (c>='0')&&(c<='9') )
        
         {
           pasajeros P1= new pasajeros("toyota",placa,50);
           System.out.println("placas: " +P1.getPlaca());
           System.out.println("pasajeros: " +P1.getCantPasajeros());	
         }	
         	else
         	{
         	  	particular P1= new particular("toyota",placa,5);
         	  	System.out.println("placas: " +P1.getPlaca());
         	  	
         	}
        
      /* if(Character.isDigit(c))
       {
           pasajeros P1= new pasajeros("toyota",placa,50);
           System.out.println("placas: " +P1.getPlaca());
           System.out.println("pasajeros: " +P1.getCantPasajeros());	
         }	
         	else
         	{
         	  	particular P1= new particular("toyota",placa,5);
         	  	System.out.println("placas: " +P1.getPlaca());
         	  	
         	}*/	

 	
 	}
 }